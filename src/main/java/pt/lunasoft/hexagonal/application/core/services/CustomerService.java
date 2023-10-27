package pt.lunasoft.hexagonal.application.core.services;

import pt.lunasoft.hexagonal.application.core.domain.Address;
import pt.lunasoft.hexagonal.application.core.domain.Customer;
import pt.lunasoft.hexagonal.application.ports.in.CustomerInputPort;
import pt.lunasoft.hexagonal.application.ports.out.AddressOutputPort;
import pt.lunasoft.hexagonal.application.ports.out.CustomerOutputPort;

public class CustomerService implements CustomerInputPort {

	private final AddressOutputPort addressOutputPort;
	private final CustomerOutputPort customerOutputPort;
	
	public CustomerService(AddressOutputPort addressOutputPort, CustomerOutputPort customerOutputPort) {
		this.addressOutputPort = addressOutputPort;
		this.customerOutputPort = customerOutputPort;
	}
	
	@Override
	public void insert(Customer customer, String zipCode) {
		Address address = this.addressOutputPort.find(zipCode);
		customer.setAddress(address);
		this.customerOutputPort.insert(customer);
		this.send(customer.getCpf());
	}
	
	@Override
	public Customer find(String id) {
		return this.customerOutputPort.find(id).orElseThrow(() -> new RuntimeException("Customer not found!"));
	}
	
	@Override
	public void update(Customer customer, String zipCode) {
		this.find(customer.getId());
		Address address = this.addressOutputPort.find(zipCode);
		customer.setAddress(address);
		this.customerOutputPort.update(customer);
	}
	
	@Override
	public void delete(String id) {
		this.find(id);
		this.customerOutputPort.delete(id);
	}
	
	@Override
	public void send(String cpf) {
		this.customerOutputPort.send(cpf);
	}
	
}