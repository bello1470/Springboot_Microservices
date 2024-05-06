//package bellotech.org.orderservice.web.exceptions.controller;
//
//
//import bellotech.org.orderservice.ApplicationProperties;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class RabbitMQDemoController {
//
//    private final RabbitTemplate rabbitTemplate;
//    private final ApplicationProperties applicationProperties;
//
//    RabbitMQDemoController(RabbitTemplate rabbitTemplate,ApplicationProperties applicationProperties ){
//
//        this.rabbitTemplate = rabbitTemplate;
//        this.applicationProperties = applicationProperties;
//    }
//
//    @PostMapping("/send")
//    public void sendMessage(@RequestBody Mymessage myMessage){
//        rabbitTemplate.convertAndSend(
//                applicationProperties.orderEventsExchange(),
//                myMessage.routingKey(),
//                myMessage.payload());
//
//    }
//}
//
//record Mymessage(String routingKey, MyPayload payload){}
//
//record  MyPayload (String content){}