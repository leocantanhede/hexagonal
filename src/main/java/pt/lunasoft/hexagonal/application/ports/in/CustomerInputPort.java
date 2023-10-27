package pt.lunasoft.hexagonal.application.ports.in;

import pt.lunasoft.hexagonal.application.core.domain.Customer;

public interface CustomerInputPort {

	void insert(Customer customer, String zipCode);
	Customer find(String id);
	
}