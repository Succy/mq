package cn.succy.mq.producer;

import cn.succy.mq.config.ConfigManager;
import cn.succy.mq.config.MqConfig;
import cn.succy.mq.core.ConnectionFactory;
import cn.succy.mq.core.MessageCreator;
import cn.succy.mq.core.MessageModel;
import cn.succy.mq.core.MqException;
import com.xiaoleilu.hutool.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.io.Serializable;

import static cn.succy.mq.core.MqConst.DEFAULT_QUEUE_SUBJECT;
import static cn.succy.mq.core.MqConst.DEFAULT_TOPIC_SUBJECT;

/**
 * @author Succy
 * @date 2017-10-29 12:45
 **/

public abstract class AbstractProducer implements Producer {
    private static final Logger logger = LoggerFactory.getLogger(AbstractProducer.class);

    private Connection connection;
    private Session session;
    private boolean isTransacted;
    private MessageProducer producer;

    private MqConfig config = ConfigManager.getConfig(MqConfig.class);

    protected AbstractProducer() {
    }

    public AbstractProducer(MessageModel model, boolean isTransacted, String subject) {
        this.connection = ConnectionFactory.getConnection();
        this.isTransacted = isTransacted;
        try {
            if (this.isTransacted) {
                session = connection.createSession(true, Session.SESSION_TRANSACTED);
            } else {
                session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            }
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
            this.producer = session.createProducer(destination);
        } catch (JMSException e) {
           logger.error("create abstract producer error", e);
            throw new MqException("create abstract producer error", e);
        }
    }

    @Override
    public void send(Message message) {
        try {
            producer.send(message);
            if (isTransacted) {
                session.commit();
            }
        } catch (JMSException e) {
            logger.error("send message error", e);
            throw new MqException("send message error", e);
        }
    }

    @Override
    public void send(String message) {
        try {
            TextMessage msg = session.createTextMessage(message);
            send(msg);
        } catch (JMSException e) {
            logger.error(e.getMessage(), e);
            throw new MqException(e);
        }
    }

    @Override
    public void send(Serializable message) {
        try {
            ObjectMessage msg = session.createObjectMessage(message);
            send(msg);
        } catch (JMSException e) {
            logger.error(e.getMessage(), e);
            throw new MqException(e);
        }
    }

    @Override
    public void send(byte[] message) {
        try {
            BytesMessage msg = session.createBytesMessage();
            msg.writeBytes(message);
            send(msg);
        } catch (JMSException e) {
            logger.error(e.getMessage(), e);
            throw new MqException(e);
        }
    }

    @Override
    public void send(MessageCreator creator) {
        try {
            Message message = creator.createMessage(session);
            send(message);
        } catch (JMSException e) {
            logger.error(e.getMessage(), e);
            throw new MqException(e);
        }
    }

    /**
     * 关闭session和connection
     * 如果采用的是单连接，该方法调用时，只关闭session，不关闭connection
     * 若采用的是池连接，则调用该方法时，关闭session和connection
     */
    @Override
    public void close() {
        try {
            this.session.close();
            if (!config.isPoolEnable()) {
                this.connection.close();
            }
        } catch (JMSException e) {
            logger.error("close session error", e);
            e.printStackTrace();
        }
    }
}
