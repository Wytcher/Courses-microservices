package br.com.authapi.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitEmailConfig {

    @Value("${app.config.rabbit.exchange.email}")
    private String emailTopicExchange;

    @Value("${app.config.rabbit.routing-key.student-registered}")
    private String studentRegisteredRoutingKey;

    @Value("${app.config.rabbit.queue.student-registered}")
    private String studentRegisteredQueue;

    @Bean
    public DirectExchange emailTopicExchange() {
        return new DirectExchange(emailTopicExchange);
    }

    @Bean
    public Queue studentRegisteredQueue() {
        return new Queue(studentRegisteredQueue, true);
    }

    @Bean
    public Binding accountRegisteredQueueBinding(DirectExchange directExchange) {
        return BindingBuilder
                .bind(studentRegisteredQueue())
                .to(directExchange)
                .with(studentRegisteredRoutingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
