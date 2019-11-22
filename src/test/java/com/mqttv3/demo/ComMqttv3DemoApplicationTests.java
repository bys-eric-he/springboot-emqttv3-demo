package com.mqttv3.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComMqttv3DemoApplicationTests {

    @Test
    public void contextLoads() throws Exception {
        for (int i = 0; i < 100000000; i++) {
            MqttServer.publish(String.format("Demo/Add/%s", i)
                    , String.format("This is the message-%s from topic CM/DeviceDailyRecord/DailyRecord/Add/#!", i)
                    , false, 0);
        }

        /*MqttServer.publish("Demo/Add/111"
                , "This is the message-111 from topic CM/DeviceDailyRecord/DailyRecord/Add/#!"
                , true, 0);

        MqttServer.publish("Demo/Add/222"
                , "This is the message-222 from topic CM/DeviceDailyRecord/DailyRecord/Add/#!"
                , true, 0);

        MqttServer.publish("Demo/Add/333"
                , "This is the message-333 from topic CM/DeviceDailyRecord/DailyRecord/Add/#!"
                , true, 0);

        MqttServer.publish("Demo/Add/444"
                , "This is the message-444 from topic CM/DeviceDailyRecord/DailyRecord/Add/#!"
                , true, 0);

        MqttServer.publish("Demo/Add/555"
                , "This is the message-555 from topic CM/DeviceDailyRecord/DailyRecord/Add/#!"
                , false, 0);

        MqttServer.publish("Demo/Save/666"
                , "This is the message-666 from topic CM/DeviceDailyRecord/DailyRecord/Add/#!"
                , false, 0);

        MqttServer.publish("Demo/Add/777"
                , "This is the message-888 from topic CM/DeviceDailyRecord/DailyRecord/Add/#!"
                , true, 0);*/
    }

}
