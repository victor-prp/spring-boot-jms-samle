package hello;


import hello.destination.Queue;
import hello.destination.Topic;
import hello.dto.AccountMsg;
import hello.dto.AccountMsgOpportunity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Component
public class AccountMsgExchange {
    private final JmsTemplate jmsTopicTemplate;
    private CountDownLatch countDownLatch;
    private CopyOnWriteArrayList<AccountMsg> msgs;

    public AccountMsgExchange(JmsTemplate jmsTopicTemplate) {
        this.jmsTopicTemplate = jmsTopicTemplate;
    }

    @JmsListener(destination = Queue.ACCOUNT_MSG, containerFactory = "queueFactory")
    public void receiveMessage(AccountMsg accountMsg) {
        countDownLatch.countDown();
        msgs.add(accountMsg);
        System.out.println("AccountMsgExchange: Received <" + accountMsg + ">");
    }

    public synchronized List<AccountMsg> msgOpportunity(String account, String context) throws InterruptedException {
        countDownLatch = new CountDownLatch(2);
        msgs = new CopyOnWriteArrayList<>();

        AccountMsgOpportunity req = new AccountMsgOpportunity(account, context);
        jmsTopicTemplate.convertAndSend(Topic.MSG_OPPORTUNITY, req);
        System.out.println("AccountMsgOpportunity sent: " + req);

        if (!countDownLatch.await(5, TimeUnit.SECONDS)){
            System.out.println("Warning! Timeout! Not all sources replied. ");
        }
        return msgs;

    }



}
