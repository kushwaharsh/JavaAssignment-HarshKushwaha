package net.sunbase.assignment.controller;
import net.sunbase.assignment.client.CustomerApiClient;
import net.sunbase.assignment.repository.CustomerRepository;
import net.sunbase.assignment.entity.Customer;
import net.sunbase.assignment.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Controller
public class CustomerController {
	private CustomerService customerService;
	private CustomerRepository customerRepository;
	public CustomerController(CustomerService customerService , CustomerRepository customerRepository, CustomerRepository customerRepository1) {
		super();
		this.customerService = customerService;
		this.customerRepository = customerRepository1;
	}
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	@GetMapping("/CustomerLogin")
	public String CustomerLogin() {
		return "CustomerLogin";
	}
	@GetMapping("/customers")
	public String listCustomers(Model model) {
		model.addAttribute("customers", customerService.getAllCustomers());
		return "customers";
	}
	@GetMapping("/customers/new")
	public String createCustomerForm(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "create_customer";
	}
	@PostMapping("/customers")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		customerService.saveCustomer(customer);
		return "redirect:/customers";
	}
	@GetMapping("/customers/edit/{id}")
	public String editCustomerForm(@PathVariable Long id, Model model) {
		model.addAttribute("customer", customerService.getCustomerById(id));
		return "edit_customer";
	}
	@PostMapping("/customers/{id}")
	public String updateCustomer(@PathVariable Long id,
								 @ModelAttribute("customer") Customer customer,
								 Model model) {
		Customer existingCustomer = customerService.getCustomerById(id);
		existingCustomer.setId(id);
		existingCustomer.setFirst_name(customer.getFirst_name());
		existingCustomer.setLast_name(customer.getLast_name());
		existingCustomer.setEmail(customer.getEmail());

		// save updated student object
		customerService.updateCustomer(existingCustomer);
		return "redirect:/customers";
	}
	@GetMapping("/customers/{id}")
	public String deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomerById(id);
		return "redirect:/customers";
	}
	@GetMapping("/search")
	public String searchCustomer(@RequestParam("field") String field,
								 @RequestParam("query") String query,
								 Model model) {
		List<Customer> searchResults = customerService.searchCustomer(field, query);
		model.addAttribute("customers", searchResults);
		return "customers";
	}
	@GetMapping("/sync")
	public ResponseEntity<Map<String, String>> syncData() {
		CustomerApiClient customerApiClient = new CustomerApiClient();
		List<Customer> customersFromApi = customerApiClient.getCustomerList();

		if (customersFromApi != null) {
			for (Customer apiCustomer : customersFromApi) {
				Optional<Customer> optionalExistingCustomer = customerRepository.findByUuid(apiCustomer.getUuid());

				if (optionalExistingCustomer.isPresent()) {
					Customer existingCustomer = optionalExistingCustomer.get();
					// Update existing customer
					existingCustomer.setFirst_name(apiCustomer.getFirst_name());
					existingCustomer.setLast_name(apiCustomer.getLast_name());
					existingCustomer.setAddress(apiCustomer.getAddress());
					existingCustomer.setCity(apiCustomer.getCity());
					existingCustomer.setState(apiCustomer.getState());
					existingCustomer.setEmail(apiCustomer.getEmail());
					existingCustomer.setPhone(apiCustomer.getPhone());
					customerRepository.save(existingCustomer);
				} else {
					// Save new customer
					customerRepository.save(apiCustomer);
				}
			}
			return ResponseEntity.ok().body(Map.of("message", "Sync successful"));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Error in syncing data"));
		}
	}


}

