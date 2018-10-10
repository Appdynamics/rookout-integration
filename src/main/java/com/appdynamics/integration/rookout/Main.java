package com.appdynamics.integration.rookout;

import org.eclipse.jetty.util.Jetty;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {
    private static final String DEFAULT_CONFIG_FILE_NAME="config.properties";
    Properties props = new Properties();
    public static void main(String [] args) {
        if (args.length < 1) {
            System.err.println("Do not have enough information to context missing args");
            System.exit(-1);
        }
        Main m = new Main();
        m. startup();
    }

    private void startup() {
        loadConfiguration();
        bootstrapServer();
    }

    private void bootstrapServer() {
        JettyWebServer jetty = new JettyWebServer();
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