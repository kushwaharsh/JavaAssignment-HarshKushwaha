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

	// handler method to handle list students and return mode and view
	@GetMapping("/students")
	public String listStudents(Model model) {
		model.addAttribute("students", customerService.getAllStudents());
		return "students";
	}

	@GetMapping("/students/new")
	public String createStudentForm(Model model) {

		// create student object to hold student form data
		Customer student = new Customer();
		model.addAttribute("student", student);
		return "create_student";

	}

	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Customer student) {
		customerService.saveStudent(student);
		return "redirect:/students";
	}

	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", customerService.getStudentById(id));
		return "edit_student";
	}

	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id,
			@ModelAttribute("student") Customer student,
			Model model) {

		// get student from database by id
		Customer existingStudent = customerService.getStudentById(id);
		existingStudent.setId(id);
		existingStudent.setFirst_name(student.getFirst_name());
		existingStudent.setLast_name(student.getLast_name());
		existingStudent.setEmail(student.getEmail());

		// save updated student object
		customerService.updateStudent(existingStudent);
		return "redirect:/students";
	}

	// handler method to handle delete student request

	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		customerService.deleteStudentById(id);
		return "redirect:/students";
	}

	@GetMapping("/search")
	public String searchCustomer(@RequestParam("field") String field,
								 @RequestParam("query") String query,
								 Model model) {
		List<Customer> searchResults = customerService.searchCustomer(field, query);
		model.addAttribute("students", searchResults);
		return "students";
	}


		@GetMapping("/sync")
		public ResponseEntity<String> syncData() {
			// Instantiate CustomerApiClient and call getCustomerList
			CustomerApiClient customerApiClient = new CustomerApiClient();
			List<Customer> customersFromApi = customerApiClient.getCustomerList();

			if (customersFromApi != null) {
				for (Customer apiCustomer : customersFromApi) {
					// Check if a customer with the same UUID already exists in the database
					Optional<Customer> optionalExistingCustomer = customerRepository.findByUuid(apiCustomer.getUuid());

					if (optionalExistingCustomer.isPresent()) {
						// Update existing customer
						Customer existingCustomer = optionalExistingCustomer.get();
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
				return ResponseEntity.ok("Sync successful");
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in syncing data");
			}
		}


}

