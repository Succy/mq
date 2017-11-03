package cn.succy.mq.producer;

import cn.succy.mq.core.MessageModel;
import cn.succy.mq.core.MqConst;

/**
 * @author Succy
 * @date 2017-10-29 12:20
 **/

public class QueueProducer extends AbstractProducer {
    public QueueProducer(boolean isTransacted, String subject) {
        super(MessageModel.QUEUE, isTransacted, subject);
    }

    public QueueProducer(String subject) {
        this(false, subject);
    }

    public QueueProducer() {
        this(MqConst.DEFAULT_QUEUE_SUBJECT);
    }
}
