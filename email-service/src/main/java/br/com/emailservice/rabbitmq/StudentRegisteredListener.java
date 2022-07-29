package br.com.emailservice.rabbitmq;

import br.com.emailservice.dto.StudentRegisteredDTO;
import br.com.emailservice.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StudentRegisteredListener {

    private final EmailService emailService;

    public StudentRegisteredListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${app.config.rabbit.queue.student-registered}")
    public void accountCreated(StudentRegisteredDTO studentRegisteredDTO) {
        log.info("Receiving message: {}", studentRegisteredDTO.toString());

        try {
            emailService.sendMail(studentRegisteredDTO.getEmail(), studentRegisteredDTO.getSubject(), studentRegisteredDTO.getMessage());
            log.info("Email sent successfully");
        } catch (Exception e) {
            log.info("Error while trying to sent the registration email: {}", e.getMessage());
        }
    }
}
