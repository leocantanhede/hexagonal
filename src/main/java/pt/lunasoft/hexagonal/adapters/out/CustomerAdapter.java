package pt.lunasoft.hexagonal.adapters.out;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import pt.lunasoft.hexagonal.adapters.out.repository.CustomerRepository;
import pt.lunasoft.hexagonal.adapters.out.repository.entity.CustomerEntity;
import pt.lunasoft.hexagonal.adapters.out.repository.mapper.CustomerMapper;
import pt.lunasoft.hexagonal.application.core.domain.Customer;
import pt.lunasoft.hexagonal.application.ports.out.CustomerOutputPort;

@Component
public class CustomerAdapter implements CustomerOutputPort {

	@Autowired
	private CustomerRepository repository;
	
	@Autowired
	private CustomerMapper mapper;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Override
	public void insert(Customer customer) {
		this.repository.save(this.mapper.toEntity(customer));
	}
	
	@Override
	public Optional<Customer> find(String id) {
		Optional<CustomerEntity> entity = this.repository.findById(id);
		return entity.isPresent() ? Optional.of(this.mapper.toDomain(entity.get())) : null;
	}
	
	@Override
	public void update(Customer customer) {
		this.repository.save(this.mapper.toEntity(customer));
	}
	
	@Override
	public void delete(String id) {
		this.repository.deleteById(id);
	}

	@Override
	public void send(String cpf) {
		this.kafkaTemplate.send("tp-cpf-validation", cpf);
	}
	
}