package com.laba3.controllers.listeners;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by root on 02.05.17.
 */
public class NewAppStartListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        PropertyConfigurator.configure(NewAppStartListener.class.
                getClassLoader().getResource("log4j.properties"));
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
