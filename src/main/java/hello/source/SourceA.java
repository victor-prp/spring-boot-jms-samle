package hello.source;

import hello.destination.Queue;
import hello.destination.Topic;
import hello.dto.AccountMsg;
import hello.dto.AccountMsgOpportunity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class SourceA {

    private final JmsTemplate jmsQueueTemplate;

    public SourceA(JmsTemplate jmsQueueTemplate) {
        this.jmsQueueTemplate = jmsQueueTemplate;
    }

    @JmsListener(destination = Topic.MSG_OPPORTUNITY, containerFactory = "topicFactory")
    public void receiveMessage(AccountMsgOpportunity opportunity) {
        System.out.println("SourceA: Received <" + opportunity + ">");

        AccountMsg msg = new AccountMsg(opportunity.getAccount(), opportunity.getContext(), "SourceA", "loan $100K");
        jmsQueueTemplate.convertAndSend(Queue.ACCOUNT_MSG, msg);
        System.out.println("Account msg sent from SourceA:"+ msg);
    }

}
