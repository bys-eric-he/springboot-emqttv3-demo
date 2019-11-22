package com.mqttv3.demo;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.*;

/**
 * MQTT服务
 * Created by eric on 2017/7/3.
 */
public class MqttServer {

    private static MqttClient client = ApplicationContextUtil.getBean(MqttClient.class);
    private static Logger logger = Logger.getLogger(MqttServer.class);

    /**
     * 发布消息
     *
     * @param topic
     * @param message
     * @param isRetained
     * @param qos
     * @throws MqttPersistenceException
     * @throws MqttException
     */
    public static void publish(String topic, String message, boolean isRetained, int qos) throws Exception {
        MqttTopic mqttTopic;
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setQos(qos);
        mqttMessage.setRetained(isRetained);
        mqttMessage.setPayload(message.getBytes());
        mqttTopic = client.getTopic(topic);

        MqttDeliveryToken token = mqttTopic.publish(mqttMessage);
        token.waitForCompletion();
        logger.info(String.format("消息发布完成%s! ", token.isComplete()));
    }

    /**
     * 订阅消息
     *
     * @param topics
     * @param qos
     * @throws Exception
     */
    public static void subscribe(String[] topics, int[] qos) throws Exception {
        client.subscribe(topics, qos);
        logger.info(String.format("订阅主题:%s成功！", String.join(",", topics)));
    }

    /**
     * 取消订阅
     *
     * @param topic
     * @throws Exception
     */
    public static void unsubscribe(String topic) throws Exception {
        client.unsubscribe(topic);
        logger.info(String.format("取消订阅成功,取消主题：%s", topic));
    }
}
