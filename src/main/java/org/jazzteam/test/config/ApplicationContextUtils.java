package org.jazzteam.test.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by Savenok.Ivan on 04.04.2017.
 */

@Component
public final class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext ctx;


    public static ApplicationContext getApplicationContext() {
        return ctx;
    }

    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {
        ctx = appContext;
    }

    public static <T> T getBean(Class<T> var1) {
        return getApplicationContext().getAutowireCapableBeanFactory().getBean(var1);
    }

}
