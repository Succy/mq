package cn.succy.mq.test;

import cn.succy.mq.producer.Producer;
import cn.succy.mq.producer.QueueProducer;
import org.junit.Test;

/**
 * @author Succy
 * @date 2017-10-29 18:16
 **/

public class ProducerTest {
    //public static void main(String[] args) {
    //    Producer producer = new QueueProducer();
    //    producer.send("测试一下，发送一条文本的消息");
    //    producer.close();
    //}

    @Test
    public void sendText() {
        Producer producer = new QueueProducer();
        User user = new User();
        user.setName("Succy");
        user.setAddr("广西柳州市");
        user.setAge(12);

        producer.send(user);
        producer.close();
    }
}
