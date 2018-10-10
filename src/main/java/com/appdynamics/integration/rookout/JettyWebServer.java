package com.appdynamics.integration.rookout;

import org.eclipse.jetty.server.Server;

public class JettyWebServer implements Runnable {
    private String endPoint;
    private int port;
    private String apiKey;

    public JettyWebServer(String endPoint,String apiKey, int port) {
        this.endPoint = endPoint;
        this.port = port;
        this.apiKey = apiKey;
    }

    @Override
    public void run() {
        Server server = new Server(port);
        server.setHandler(new WebhookHandler(endPoint,apiKey));
        try {
            server.start();
            server.dumpStdErr();
            server.join();
        } catch ( Exception ex) {
            ex.printStackTrace();
        }
    }
}
