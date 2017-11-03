package cn.succy.mq.core;

/**
 * jms的mq支持两种消息模型: 1、p2p，2、pub/sub
 *
 * @author Succy
 */
public enum MessageModel {
    /**
     * 队列模型
     */
    QUEUE("queue"),
    /**
     * 发布订阅模型
     */
    TOPIC("topic");
    private String type;

    MessageModel(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
