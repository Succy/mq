package cn.succy.mq.config;

/**
 * mq的配置文件加载类
 *
 * @author Succy
 * @date 2017-10-25 21:45
 **/
@PropertiesConfig(prefix = "mq")
public class MqConfig {
    private String type;
    private String brokerUrl;
    private String username;
    private String password;
    private boolean poolEnable;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrokerUrl() {
        return brokerUrl;
    }

    public void setBrokerUrl(String brokerUrl) {
        this.brokerUrl = brokerUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPoolEnable() {
        return poolEnable;
    }

    public void setPoolEnable(boolean poolEnable) {
        this.poolEnable = poolEnable;
    }

    @Override
    public String toString() {
        return "MqConfig{" +
                "type='" + type + '\'' +
                ", brokerUrl='" + brokerUrl + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
