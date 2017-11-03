package cn.succy.mq.core;

import cn.succy.mq.config.ConfigManager;
import cn.succy.mq.config.MqConfig;
import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnection;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Connection;
import javax.jms.JMSException;

/**
 * 连接工厂
 *
 * @author Succy
 * @date 2017-10-25 20:04
 **/

public final class ConnectionFactory {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);
    private static MqConfig config = ConfigManager.getConfig(MqConfig.class);
    private static Connection connection = null;
    private static ActiveMQConnectionFactory factory = null;
    private static PooledConnectionFactory pooledConnectionFactory = null;

    static  {
        String brokerUrl = config.getBrokerUrl();
        String username = config.getUsername();
        String password = config.getPassword();
        if (StrUtil.isBlank(brokerUrl)) {
            logger.error("broker url is blank!");
            throw new MqException("broker url is blank");
        }
        factory = new ActiveMQConnectionFactory(brokerUrl);
        if (StrUtil.isNotBlank(username) && StrUtil.isNotBlank(password)) {
            factory.setUserName(username);
            factory.setPassword(password);
        }
        factory.setTrustAllPackages(true);
        pooledConnectionFactory = new PooledConnectionFactory(factory);
    }

    private ConnectionFactory() {}

    /**
     * 根据配置，来动态获取单连接对象还是池连接对象
     * @return
     */
    public static Connection getConnection() {
        if (config.isPoolEnable()) {
            return getSingleConnection();
        }
        return getPooledConnection();
    }

    /**
     * 获取单链接对象，连接对象不会被释放
     * @return 连接对象
     */
    public static Connection getSingleConnection() {
        if (connection == null) {
            try {
                connection = factory.createConnection();
                connection.start();
            } catch (JMSException e) {
                logger.error("create ActiveMq singleConnection error!");
                throw new MqException("create ActiveMq singleConnection error!");
            }
        }
        return connection;
    }

    /**
     * 从连接池中获取连接对象，释放后，放入连接池
     * @return 连接对象
     */
    public static Connection getPooledConnection() {
        PooledConnection pooledConnection;
        try {
            pooledConnection = (PooledConnection) pooledConnectionFactory.createConnection();
            pooledConnection.start();
        } catch (JMSException e) {
            logger.error("create ActiveMq pooledConnection error!");
            throw new MqException("create ActiveMq pooledConnection error!");
        }
        return pooledConnection;
    }
}
