package pt.lunasoft.hexagonal.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import pt.lunasoft.hexagonal.adapters.in.consumer.message.CustomerMessage;
import pt.lunasoft.hexagonal.application.core.services.CustomerService;
import pt.lunasoft.hexagonal.application.ports.out.AddressOutputPort;
import pt.lunasoft.hexagonal.application.ports.out.CustomerOutputPort;

@EnableKafka
@Configuration
public class Config {

    @Bean
    CustomerService customerService(AddressOutputPort addressOutputPort, CustomerOutputPort customerOutputPort) {
		return new CustomerService(addressOutputPort, customerOutputPort);
	}
	
    @Bean
    ProducerFactory<String, String> producerFactory() {
    	Map<String, Object> config = new HashMap<>();
    	config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    	config.put(ConsumerConfig.GROUP_ID_CONFIG, "lunasoft");
    	config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    	config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    	return new DefaultKafkaProducerFactory<>(config);
    }
    
    @Bean
    KafkaTemplate<String, String> kafkaTemplate() {
    	return new KafkaTemplate<>(producerFactory());
    }
    
    @Bean
    ConsumerFactory<String, CustomerMessage> consumerFactory() {
    	Map<String, Object> config = new HashMap<>();
    	config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    	config.put(ConsumerConfig.GROUP_ID_CONFIG, "lunasoft");
    	config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    	config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    	config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    	return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(CustomerMessage.class));
    }
    
    @Bean
    ConcurrentKafkaListenerContainerFactory<String, CustomerMessage> kafkaListenerContainerFactory() {
    	ConcurrentKafkaListenerContainerFactory<String, CustomerMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
    	factory.setConsumerFactory(consumerFactory());
    	return factory;
    }
    
}