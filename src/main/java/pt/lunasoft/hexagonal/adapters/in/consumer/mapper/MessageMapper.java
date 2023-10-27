package pt.lunasoft.hexagonal.adapters.in.consumer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import pt.lunasoft.hexagonal.adapters.in.consumer.message.CustomerMessage;
import pt.lunasoft.hexagonal.application.core.domain.Customer;

@Mapper(componentModel = "spring")
public interface MessageMapper {

	@Mapping(target = "address", ignore = true)
	Customer toDomain(CustomerMessage message);
	
}