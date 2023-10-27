package pt.lunasoft.hexagonal.adapters.in.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import pt.lunasoft.hexagonal.adapters.in.controller.mapper.RequestMapper;
import pt.lunasoft.hexagonal.adapters.in.controller.mapper.ResponseMapper;
import pt.lunasoft.hexagonal.adapters.in.controller.request.CustomerRequest;
import pt.lunasoft.hexagonal.adapters.in.controller.response.CustomerResponse;
import pt.lunasoft.hexagonal.application.core.domain.Customer;
import pt.lunasoft.hexagonal.application.ports.in.CustomerInputPort;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

	@Autowired
	private CustomerInputPort inputPort;
	
	@Autowired
	private RequestMapper requestMapper;
	
	@Autowired
	private ResponseMapper responseMapper;
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CustomerRequest request) {
		Customer customer = this.requestMapper.toDomain(request);
		this.inputPort.insert(customer,request.getZipCode());
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerResponse> find(@PathVariable final String id) {
		Customer customer = this.inputPort.find(id);
		return ResponseEntity.ok().body(this.responseMapper.toResponse(customer));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable final String id, @Valid @RequestBody CustomerRequest request) {
		Customer customer = this.requestMapper.toDomain(request);
		customer.setId(id);
		this.inputPort.update(customer, request.getZipCode());
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable final String id) {
		this.inputPort.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}