package cn.succy.mq.test;

import cn.succy.mq.consumer.Consumer;
import cn.succy.mq.consumer.QueueConsumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import java.io.Serializable;

/**
 * @author Succy
 * @date 2017-10-27 22:25
 **/

public class ConsumerTest {

    public static void main(String[] args) throws Exception {
        Consumer consumer = new QueueConsumer();
        consumer.receive(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                ObjectMessage msg = (ObjectMessage) message;
                try {
                    Serializable object = msg.getObject();
                    User user = (User) object;
                    System.out.println(user);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
