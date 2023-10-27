package pt.lunasoft.hexagonal.adapters.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.lunasoft.hexagonal.adapters.out.client.AddressClient;
import pt.lunasoft.hexagonal.adapters.out.client.mapper.AddressMapper;
import pt.lunasoft.hexagonal.adapters.out.client.response.AddressResponse;
import pt.lunasoft.hexagonal.application.core.domain.Address;
import pt.lunasoft.hexagonal.application.ports.out.AddressOutputPort;

@Component
public class AddressAdapter implements AddressOutputPort {

	@Autowired
	private AddressClient client;
	
	@Autowired
	private AddressMapper mapper;
	
	@Override
	public Address find(String zipCode) {
		AddressResponse response = this.client.find(zipCode);
		return this.mapper.toAddress(response);
	}

}