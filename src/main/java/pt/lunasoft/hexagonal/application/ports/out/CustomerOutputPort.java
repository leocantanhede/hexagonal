package pt.lunasoft.hexagonal.application.ports.out;

import java.util.Optional;

import pt.lunasoft.hexagonal.application.core.domain.Customer;

public interface CustomerOutputPort {

	void insert(Customer customer);
	Optional<Customer> find(String id);
	void update(Customer customer);
	void delete(String id);
	void send(String cpf);
	
}