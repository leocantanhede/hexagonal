package pt.lunasoft.hexagonal.adapters.in.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import pt.lunasoft.hexagonal.adapters.in.controller.request.CustomerRequest;
import pt.lunasoft.hexagonal.application.core.domain.Customer;

@Mapper(componentModel = "spring")
public interface RequestMapper {

	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "address", ignore = true),
		@Mapping(target = "isValidCpf", ignore = true)
	})
	Customer toDomain(CustomerRequest request);

}