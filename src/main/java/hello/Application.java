
package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // Launch the application
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        // Send a message with a POJO - the template reuse the message converter
        System.out.println("Sending email messages to 'mailbox' topic");
        for (int i = 0; i < 10; i++){
            String msg = "Hello-"+i;
            jmsTemplate.convertAndSend("mailbox", new Email("info@example.com", msg));
            System.out.println("Msg sent: "+msg);
        }
    }

}
