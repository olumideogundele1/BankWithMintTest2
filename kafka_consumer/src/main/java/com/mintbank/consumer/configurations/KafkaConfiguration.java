package com.mintbank.consumer.configurations;

import com.mintbank.consumer.dto.MintResponse;
import lombok.val;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfiguration {

    @Autowired
    Environment environment;

    @Bean
    public ConsumerFactory<String, MintResponse> consumerFactory(){
        val deserilizer = new JsonDeserializer<MintResponse>();
        deserilizer.addTrustedPackages("com.mintbank.consumer.dto.MintResponse","com.test.mintbank.dtos.MintResponse");
        deserilizer.addTrustedPackages("*");

        Map<String,Object> map = new HashMap<>();
        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,environment.getProperty("spring.kafka.bootstrap-servers"));
        map.put(ConsumerConfig.GROUP_ID_CONFIG,environment.getProperty("spring.kafka.consumer.group-id"));
        map.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,environment.getProperty("spring.kafka.consumer.auto-offset-reset"));
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);


        return new DefaultKafkaConsumerFactory<>(map, new StringDeserializer(), new JsonDeserializer<>(MintResponse.class,false));

    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,MintResponse> listenerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,MintResponse> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
