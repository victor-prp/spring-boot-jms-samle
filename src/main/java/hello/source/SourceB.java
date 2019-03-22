package hello.source;

import hello.destination.Queue;
import hello.dto.AccountMsg;
import hello.dto.AccountMsgOpportunity;
import hello.destination.Topic;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


@Component
public class SourceB {
    private final JmsTemplate jmsQueueTemplate;

    public SourceB(JmsTemplate jmsQueueTemplate) {
        this.jmsQueueTemplate = jmsQueueTemplate;
    }
    @JmsListener(destination = Topic.MSG_OPPORTUNITY, containerFactory = "topicFactory")
    public void receiveMessage(AccountMsgOpportunity opportunity) {
        System.out.println("SourceB: Received <" + opportunity + ">");

        AccountMsg msg = new AccountMsg(opportunity.getAccount(), opportunity.getContext(), "SourceB", "your overdraft is too big $25K!!!");
        jmsQueueTemplate.convertAndSend(Queue.ACCOUNT_MSG, msg);
        System.out.println("Account msg sent from SourceB:"+ msg);
    }

}
