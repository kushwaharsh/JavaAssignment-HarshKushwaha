package net.sunbase.assignment.service;
import java.util.List;
import net.sunbase.assignment.entity.Customer;
public interface CustomerService {

	List<Customer> getAllCustomers();
	Customer saveCustomer(Customer customer);
	Customer getCustomerById(Long id);
	Customer updateCustomer(Customer customer);
	void deleteCustomerById(Long id);
	List<Customer> searchCustomer(String field, String query);

}
