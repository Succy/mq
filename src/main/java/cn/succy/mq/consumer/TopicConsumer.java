package cn.succy.mq.consumer;

import cn.succy.mq.core.MessageModel;
import cn.succy.mq.core.MqConst;

/**
 * @author Succy
 * @date 2017-10-29 12:50
 **/

public class TopicConsumer extends AbstractConsumer {
    public TopicConsumer() {
        this(MqConst.DEFAULT_TOPIC_SUBJECT);
    }

    public TopicConsumer(String subject) {
        super(MessageModel.TOPIC, subject);
    }
}
