package br.com.authapi.rabbitmq;

import br.com.authapi.dto.rabbitmq.StudentRegisteredDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StudentRabbitMQProducer {

    @Value("${app.config.rabbit.exchange.email}")
    private String emailTopicExchange;

    @Value("${app.config.rabbit.routing-key.student-registered}")
    private String accountRegisteredRoutingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendAccountRegisteredMessage(StudentRegisteredDTO message) {
        try {
            log.info("Sending message: {}", message.toString());
            rabbitTemplate.convertAndSend(emailTopicExchange, accountRegisteredRoutingKey, message);
            log.info("Message was sent successfully");
        } catch (Exception e) {
            log.info("Erro while trying to send sales confirmation message: ", e);
        }
    }

}
