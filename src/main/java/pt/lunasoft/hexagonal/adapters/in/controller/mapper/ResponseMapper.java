package pt.lunasoft.hexagonal.adapters.in.controller.mapper;

import org.mapstruct.Mapper;

import pt.lunasoft.hexagonal.adapters.in.controller.response.CustomerResponse;
import pt.lunasoft.hexagonal.application.core.domain.Customer;

@Mapper(componentModel = "spring")
public interface ResponseMapper {

	CustomerResponse toResponse(Customer domain);
	
}