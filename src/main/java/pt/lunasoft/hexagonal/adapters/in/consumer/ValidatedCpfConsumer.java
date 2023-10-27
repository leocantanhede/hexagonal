package pt.lunasoft.hexagonal.adapters.in.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import pt.lunasoft.hexagonal.adapters.in.consumer.mapper.MessageMapper;
import pt.lunasoft.hexagonal.adapters.in.consumer.message.CustomerMessage;
import pt.lunasoft.hexagonal.application.ports.in.CustomerInputPort;

@Component
public class ValidatedCpfConsumer {

	@Autowired
	private CustomerInputPort inputPort;
	
	@Autowired
	private MessageMapper mapper;
	
	@KafkaListener(topics = "tp-cpf-validated", groupId = "lunasoft", containerFactory = "kafkaListenerContainerFactory")
	public void receive(CustomerMessage message) {
		this.inputPort.update(this.mapper.toDomain(message), message.getZipCode());
	}
	
}