package pt.lunasoft.hexagonal.adapters.out.repository.mapper;

import org.mapstruct.Mapper;

import pt.lunasoft.hexagonal.adapters.out.repository.entity.CustomerEntity;
import pt.lunasoft.hexagonal.application.core.domain.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

	CustomerEntity toEntity(Customer customer);
	Customer toDomain(CustomerEntity entity);
	
}