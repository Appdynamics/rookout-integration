/**
 * Copyright 2018. AppDynamics LLC and its affiliates.
 * All Rights Reserved.
 * This is unpublished proprietary source code of AppDynamics LLC and its affiliates.
 * The copyright notice above does not evidence any actual or intended publication of such source code.
 *
 *
 * Simple Web Server to handle alerts
 * @author aleftik
 * @version 1.0
 */
package com.appdynamics.integration.rookout;

import org.eclipse.jetty.server.Server;

import java.util.Properties;
import java.util.logging.Logger;

public class JettyWebServer implements Runnable {
    private static final int DEFAULT_PORT = 8888;
    private static final Logger logger = Logger.getLogger(JettyWebServer.class.getName());
    private Properties props;

    public JettyWebServer(Properties props) {
        this.props  = props;
    }

    @Override
    public void run() {
        int port = DEFAULT_PORT;
        String portConfig = props.getProperty("webserver.port");
        try {
            port = Integer.parseInt(portConfig);
            logger.severe("Listening on port " + port);
        } catch(NumberFormatException nfe) {
            logger.severe("No port configuration found using default port " + DEFAULT_PORT);
        }
        Server server = new Server(port);
        server.setHandler(new WebhookHandler(props));
        try {
            server.start();
            server.dumpStdErr();
            server.join();
        } catch ( Exception ex) {
            ex.printStackTrace();
        }
    }
}
