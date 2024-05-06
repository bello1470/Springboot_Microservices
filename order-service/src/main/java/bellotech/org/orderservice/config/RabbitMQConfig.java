package bellotech.org.orderservice.config;

import bellotech.org.orderservice.ApplicationProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
class RabbitMQConfig {

    private final ApplicationProperties applicationProperties;

     RabbitMQConfig(ApplicationProperties applicationProperties){

        this.applicationProperties = applicationProperties;
    }

    // Defining exchange
    @Bean
    DirectExchange exchange() {
        return new DirectExchange(applicationProperties.orderEventsExchange());
    }

    // defining the queue
    @Bean
    Queue newOrdersQueue(){

        return QueueBuilder.durable(applicationProperties.newOrdersQueue()).build();
    }

    // binding the queue to the exchange with the router key
    @Bean
    Binding newOrdersQueueBinding(){
        return BindingBuilder.bind(newOrdersQueue())
                .to(exchange())
                .with(applicationProperties.newOrdersQueue());
    }

    @Bean
    Queue deliveredOrdersQueue(){

        return QueueBuilder.durable(applicationProperties.deliveredOrdersQueue()).build();
    }

    @Bean
    Binding deliveredOrdersQueueBinding(){
        return BindingBuilder.bind(deliveredOrdersQueue())
                .to(exchange())
                .with(applicationProperties.deliveredOrdersQueue());
    }

    @Bean
    Queue cancelledOrdersQueue(){

        return QueueBuilder.durable(applicationProperties.cancelledOrdersQueue()).build();
    }

    @Bean
    Binding cancelOrdersQueueBinding(){
        return BindingBuilder.bind(cancelledOrdersQueue())
                .to(exchange())
                .with(applicationProperties.cancelledOrdersQueue());
    }


    @Bean
    Queue errorOrdersQueue(){

        return QueueBuilder.durable(applicationProperties.errorOrdersQueue()).build();
    }



    @Bean
    Binding errorOrdersQueueBinding(){
        return BindingBuilder.bind(errorOrdersQueue())
                .to(exchange())
                .with(applicationProperties.errorOrdersQueue());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, ObjectMapper objectMapper){
         final var rabbitTemplate = new RabbitTemplate(connectionFactory);
         rabbitTemplate.setMessageConverter(jacksonConverter(objectMapper));
         return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jacksonConverter(ObjectMapper mapper) {
         return new Jackson2JsonMessageConverter(mapper);
    }

}
