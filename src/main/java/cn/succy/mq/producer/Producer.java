package cn.succy.mq.producer;

import cn.succy.mq.core.MessageCreator;

import javax.jms.Message;
import java.io.Serializable;

/**
 * 生产者
 *
 * @author Succy
 * @date 2017-10-29 12:12
 **/

public interface Producer {
    /**
     * 直接发送Message类型的消息
     * @param message 消息
     */
    void send(Message message);

    /**
     * 发送字符类型的消息
     * @param message 消息
     */
    void send(String message);

    /**
     * 发送javabean类型消息,但是必须实现序列化
     * @param message 消息
     */
    void send(Serializable message);

    /**
     * 发送二进制类型消息
     * @param message 消息
     */
    void send(byte[] message);

    /**
     * 提供一个消息构造者构造消息并发送
     * @param creator 消息构造者
     */
    void send(MessageCreator creator);

    void close();
}
