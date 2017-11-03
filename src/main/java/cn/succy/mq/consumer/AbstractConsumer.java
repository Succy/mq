package cn.succy.mq.consumer;

import cn.succy.mq.core.ConnectionFactory;
import cn.succy.mq.core.MessageModel;
import cn.succy.mq.core.MqException;
import com.xiaoleilu.hutool.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import static cn.succy.mq.core.MqConst.DEFAULT_QUEUE_SUBJECT;
import static cn.succy.mq.core.MqConst.DEFAULT_TOPIC_SUBJECT;

/**
 * @author Succy
 * @date 2017-10-29 12:50
 **/

public abstract class AbstractConsumer implements Consumer {
    private static final Logger logger = LoggerFactory.getLogger(AbstractConsumer.class);

    private Connection connection;
    private Session session;
    private MessageConsumer consumer;

    protected AbstractConsumer() {
    }

    public AbstractConsumer(MessageModel model, String subject) {
        this.connection = ConnectionFactory.getConnection();
        try {
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = null;
            if (model == MessageModel.QUEUE) {
                if (StrUtil.isBlank(subject)) {
                    subject = DEFAULT_QUEUE_SUBJECT;
                }
                destination = session.createQueue(subject);
            } else if (model == MessageModel.TOPIC) {
                if (StrUtil.isBlank(subject)) {
                    subject = DEFAULT_TOPIC_SUBJECT;
                }
                destination = session.createTopic(subject);
            }
            this.consumer = session.createConsumer(destination);
        } catch (JMSException e) {
            logger.error("create abstract consumer error", e);
            throw new MqException("create abstract consumer error", e);
        }
    }

    @Override
    public Message receive() {
        Message message;
        try {
            message = consumer.receive();
        } catch (JMSException e) {
            logger.error(e.getMessage(), e);
            throw new MqException(e);
        }
        return message;
    }

    @Override
    public void receive(MessageListener listener) {
        try {
            consumer.setMessageListener(listener);
        } catch (JMSException e) {
            logger.error(e.getMessage(), e);
            throw new MqException(e);
        }
    }

    @Override
    public void close() {
        try {
            this.session.close();
            connection.close();
        } catch (JMSException e) {
            logger.error("close session error", e);
            e.printStackTrace();
        }
    }
}
