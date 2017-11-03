package cn.succy.mq.core;

/**
 * @author Succy
 */
public interface MqConst {
    /**
     * ActiveMq
     */
    String ACTIVEMQ = "ActiveMq";
    /**
     * RabbitMq
     */
    String RABBITMQ = "RabbitMq";

    String DEFAULT_QUEUE_SUBJECT = "mq_queue";
    String DEFAULT_TOPIC_SUBJECT = "mq_topic";
}
