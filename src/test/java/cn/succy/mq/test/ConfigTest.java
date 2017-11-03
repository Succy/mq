package cn.succy.mq.test;

import cn.succy.mq.config.ConfigManager;
import cn.succy.mq.config.MqConfig;
import org.junit.Test;

/**
 * @author Succy
 * @date 2017-10-11 22:59
 **/

public class ConfigTest {
    @Test
    public void test() {
        MqConfig config = ConfigManager.getConfig(MqConfig.class);
        System.out.println(config);
    }
}
