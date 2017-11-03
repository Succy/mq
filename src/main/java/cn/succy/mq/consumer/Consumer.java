package cn.succy.mq.consumer;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @author Succy
 * @date 2017-10-29 12:18
 **/

public interface Consumer {
    /**
     * 接收消息
     * @return 接收到的消息
     */
    Message receive();

    /**
     * 接收消息
     * @param listener 监听器
     */
    void receive(MessageListener listener);

    /**
     * 关闭当前会话资源
     */
    void close();
}
