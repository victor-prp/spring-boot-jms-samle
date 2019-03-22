
package hello;

import hello.dto.AccountMsg;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.StringJoiner;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws InterruptedException {
        // Launch the application
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        AccountMsgExchange accountMsgExchange = context.getBean(AccountMsgExchange.class);

        List<AccountMsg> accountMsgs =  accountMsgExchange.msgOpportunity("test-account", "test-context");

        System.out.println("AccountMsgs : " + toString(accountMsgs));
    }

    private static String toString(List<AccountMsg> msgs){
        StringJoiner str = new StringJoiner(" ; ");
        msgs.forEach(msg -> str.add(msg.toString()));
        return msgs.toString();
    }

}
