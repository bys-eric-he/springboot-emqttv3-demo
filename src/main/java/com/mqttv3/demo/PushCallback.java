package com.mqttv3.demo;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.*;

/**
 * 发布消息的回调类
 * Created by eric on 2017/7/3.
 */
public class PushCallback implements MqttCallbackExtended {
    private static Logger logger = Logger.getLogger(PushCallback.class);

    /**
     * 连接丢失后
     *
     * @param cause
     */
    public void connectionLost(Throwable cause) {
        logger.info(String.format("连接断开.....%s", cause.getMessage()));
    }

    /**
     * 订阅后收到到的消息
     *
     * @param topic
     * @param mqttMessage
     * @throws Exception
     */
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        //subscribe后得到的消息会执行到这里面
        logger.info(String.format("接收消息主题:%s", topic));
        logger.info(String.format("接收消息Qos:%s", mqttMessage.getQos()));
        logger.info(String.format("接收消息内容:%s", new String(mqttMessage.getPayload())));
    }

    /**
     * 消息发布成功后
     *
     * @param iMqttDeliveryToken
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        logger.info(String.format("消息发送成功:%s", iMqttDeliveryToken.isComplete()));
    }

    /**
     * 连接成功后的回调
     *
     * @param b
     * @param s
     */
    @Override
    public void connectComplete(boolean b, String s) {
        try {
            //启动服务时订阅
            String[] topics = {"Demo/Add/#", "Demo/Save/#"};
            int[] qos = {2, 2};
            MqttServer.subscribe(topics, qos);
        } catch (Exception exception) {
            logger.info(String.format("消息订阅失败,异常信息%s", exception.getMessage()));
        }
    }
}