package com.mqttv3.demo;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MQTT配置
 * Created by eric on 2017/7/3.
 */
@Configuration
public class MqttConfig {
    @Value("${mqtt.host}")
    private String serviceHost;

    @Value("${mqtt.username}")
    private String userName;

    @Value("${mqtt.password}")
    private String password;

    @Value("${mqtt.clientId}")
    private String clientId;

    @Value("${mqtt.cleanStart}")
    private boolean cleanStart;

    private MqttClient client;
    private MqttConnectOptions options;
    private static Logger logger = Logger.getLogger(MqttConfig.class);

    /**
     * 获取一个MQTT客户端连接
     *
     * @return
     * @throws Exception
     */
    @Bean
    public MqttClient getClient() throws Exception {
        client = new MqttClient(serviceHost, clientId, new MemoryPersistence());

        // MQTT的连接设置
        options = new MqttConnectOptions();
        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
        options.setCleanSession(false);
        // 设置断开后自动重连
        options.setAutomaticReconnect(true);
        /*

        // 设置连接的用户名
        options.setUserName(userName);
        // 设置连接的密码
        options.setPassword(password.toCharArray());

        // 设置超时时间 单位为秒
        options.setConnectionTimeout(10);
        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        options.setKeepAliveInterval(20);

        */

        //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
        options.setWill("demo/will-message", "close".getBytes(), 2, true);
        // 设置回调
        client.setCallback(new PushCallback());
        //连接到服务器
        client.connect(options);

        boolean isConnected = client.isConnected();

        logger.info(isConnected ? String.format("成功连接到 %s", serviceHost) : "连接失败...");
        return client;
    }
}
