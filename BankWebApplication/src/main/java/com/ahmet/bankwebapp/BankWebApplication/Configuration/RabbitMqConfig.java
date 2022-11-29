package com.ahmet.bankwebapp.BankWebApplication.Configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${spring.rabbitmq.exchange}")
    String exchange;

    @Value("${spring.rabbitmq.queue}")
    String firstStepQueue;

   @Value("${spring.rabbitmq.queue2}")
   String secondStepQueue;

    @Value("${spring.rabbitmq.queue3}")
    String thirdStepQueue;

    @Value("${spring.rabbitmq.routingKey}")
    String firstRoutingKey;

    String secondRoutingKey;

    @Bean
    DirectExchange exchange(){

        return new DirectExchange(exchange);
    }

    @Bean
    Queue firstStepQueue(){

        return new Queue(firstStepQueue , false);
    }

    @Bean
    Queue secondStepQueue(){

        return new Queue(secondStepQueue , false);
    }

    @Bean
    Queue thirdStepQueue(){

        return new Queue(thirdStepQueue , false);
    }



    @Bean
    Binding firstBinding(Queue firstStepQueue ,DirectExchange exchange){

        return BindingBuilder.bind(firstStepQueue).to(exchange).with(firstRoutingKey);
    }

    @Bean
    Binding secondBinding(Queue secondStepQueue , DirectExchange exchange){

        return BindingBuilder.bind(secondStepQueue).to(exchange).with(secondRoutingKey);
    }

    @Bean
    Binding thirdStepBinding(Queue thirdStepQueue , DirectExchange exchange){

        return BindingBuilder.bind(thirdStepQueue).to(exchange).with("thirdRoutingKey");
    }

    @Bean
    public MessageConverter jsonMessageConverter(){

        return new Jackson2JsonMessageConverter();
    }

}
