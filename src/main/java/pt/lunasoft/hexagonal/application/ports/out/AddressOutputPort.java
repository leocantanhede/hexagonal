package pt.lunasoft.hexagonal.application.ports.out;

import pt.lunasoft.hexagonal.application.core.domain.Address;

public interface AddressOutputPort {

	Address find(String zipCode);
	
}