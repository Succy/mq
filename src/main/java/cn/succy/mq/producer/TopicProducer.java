package cn.succy.mq.producer;

import cn.succy.mq.core.MessageModel;
import cn.succy.mq.core.MqConst;

/**
 * @author Succy
 * @date 2017-10-29 12:21
 **/

public class TopicProducer extends AbstractProducer {
    public TopicProducer(boolean isTransacted, String subject) {
        super(MessageModel.TOPIC, isTransacted, subject);
    }

    public TopicProducer(String subject) {
        this(false, subject);
    }

    public TopicProducer() {
        this(MqConst.DEFAULT_TOPIC_SUBJECT);
    }

}
