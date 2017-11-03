package cn.succy.mq.consumer;

import cn.succy.mq.core.MessageModel;
import cn.succy.mq.core.MqConst;

/**
 * @author Succy
 * @date 2017-10-29 12:20
 **/

public class QueueConsumer extends AbstractConsumer {
    public QueueConsumer() {
        this(MqConst.DEFAULT_QUEUE_SUBJECT);
    }

    public QueueConsumer(String subject) {
        super(MessageModel.QUEUE, subject);
    }
}
