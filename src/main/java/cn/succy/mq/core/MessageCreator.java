package cn.succy.mq.core;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * 消息构造者，用于构造消息
 * @author Succy
 */
public interface MessageCreator {
    /**
     * 构造消息
     * @param session 消息会话
     * @return
     * @throws JMSException
     */
    Message createMessage(Session session) throws JMSException;
}
