/**
 * Copyright 2018. AppDynamics LLC and its affiliates.
 * All Rights Reserved.
 * This is unpublished proprietary source code of AppDynamics LLC and its affiliates.
 * The copyright notice above does not evidence any actual or intended publication of such source code.
 *
 * Main for Rookout integration
 * @author aleftik
 * @version 1.0
 */
package com.appdynamics.integration.rookout;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class Main {
    private static final String DEFAULT_CONFIG_FILE_NAME="config.properties";
    Properties props = new Properties();
    public static void main(String [] args) {
        Main m = new Main();
        m. startup();
    }

    private void startup() {
        loadConfiguration();
        bootstrapServer();
    }

    private void bootstrapServer() {
        JettyWebServer jetty = new JettyWebServer(props);
        Thread launchingThread = new Thread(jetty);
        launchingThread.start();
        try {
            launchingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void loadConfiguration() {
        String configFile = System.getProperty("config.file");
        if (configFile == null) {
            configFile = DEFAULT_CONFIG_FILE_NAME;
        }
        try {
            props.load(new FileReader(new File(configFile)));
        } catch (IOException e) {
           throw new IllegalStateException("No Configuration file file at: " + configFile);
        }
    }
}