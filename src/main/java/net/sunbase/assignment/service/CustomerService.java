package net.sunbase.assignment.service;

import java.util.List;

import net.sunbase.assignment.entity.Customer;

public interface CustomerService {



	List<Customer> getAllStudents();
	
	Customer saveStudent(Customer student);
	
	Customer getStudentById(Long id);
	
	Customer updateStudent(Customer student);
	
	void deleteStudentById(Long id);

	List<Customer> searchCustomer(String field, String query);

}
