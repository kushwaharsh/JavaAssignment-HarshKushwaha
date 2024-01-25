package net.sunbase.assignment.service.impl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import net.sunbase.assignment.entity.Customer;
import net.sunbase.assignment.repository.CustomerRepository;
import net.sunbase.assignment.service.CustomerService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
	private CustomerRepository customerRepository;
	@PersistenceContext
	private EntityManager entityManager;
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}
	@Override
	public List<Customer> searchCustomer(String field, String query) {
		String sqlQuery = "SELECT c FROM Customer c WHERE ";
		switch (field) {
			case "first_name":
				sqlQuery += "c.first_name LIKE CONCAT('%', :query, '%')";
				break;
			case "email":
				sqlQuery += "c.email LIKE CONCAT('%', :query, '%')";
				break;
			case "phone":
				sqlQuery += "c.phone LIKE CONCAT('%', :query, '%')";
				break;
			case "city":
				sqlQuery += "c.city LIKE CONCAT('%', :query, '%')";
				break;
			// Add more cases for other fields if needed
		}
		TypedQuery<Customer> typedQuery = entityManager.createQuery(sqlQuery, Customer.class);
		typedQuery.setParameter("query", query);

		// Execute the query and return the results
		return typedQuery.getResultList();
	}
	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	@Override
	public Customer getCustomerById(Long id) {
		return customerRepository.findById(id).get();
	}
	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	@Override
	public void deleteCustomerById(Long id) {
		customerRepository.deleteById(id);
	}



}
