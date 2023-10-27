package pt.lunasoft.hexagonal.adapters.out.client.mapper;

import org.mapstruct.Mapper;

import pt.lunasoft.hexagonal.adapters.out.client.response.AddressResponse;
import pt.lunasoft.hexagonal.application.core.domain.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {

	Address toAddress(AddressResponse response);
	
}