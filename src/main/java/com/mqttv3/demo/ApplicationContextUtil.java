package com.mqttv3.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by eric on 2017/7/3.
 */
@Component
public class ApplicationContextUtil {
    private static ApplicationContextUtil instance;
    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void ApplicationContextUtil() {
        instance = this;
    }

    public static <T> T getBean(Class<T> clazz) {
        return instance.applicationContext.getBean(clazz);
    }
}
