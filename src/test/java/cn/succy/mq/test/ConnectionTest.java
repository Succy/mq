package cn.succy.mq.test;

import cn.succy.mq.core.ConnectionFactory;
import org.junit.Test;

import javax.jms.Connection;

/**
 * @author Succy
 * @date 2017-10-26 18:56
 **/

public class ConnectionTest {
    @Test
    public void getConn() {
        Connection connection = ConnectionFactory.getConnection();
        System.out.println(connection);
    }
}
