package pt.lunasoft.hexagonal.application.ports.in;

import pt.lunasoft.hexagonal.application.core.domain.Customer;

public interface CustomerInputPort {

	void insert(Customer customer, String zipCode);
	Customer find(String id);
	void update(Customer customer, String zipCode);
	void delete(String id);
	void send(String cpf);
	
}