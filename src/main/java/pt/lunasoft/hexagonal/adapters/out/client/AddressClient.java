package pt.lunasoft.hexagonal.adapters.out.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pt.lunasoft.hexagonal.adapters.out.client.response.AddressResponse;

@FeignClient(name = "AddressClient", url = "${lunasoft.client.address.url}")
public interface AddressClient {

	@GetMapping("/{zipCode}")
	AddressResponse find(@PathVariable("zipCode") String zipCode);
	
}