package br.com.emailservice.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitEmailConfig {

    @Value("${app.config.rabbit.queue.student-registered}")
    private String accountRegisteredQueue;

    @Bean
    public Queue accountRegisteredQueue() {
        return new Queue(accountRegisteredQueue, true);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
