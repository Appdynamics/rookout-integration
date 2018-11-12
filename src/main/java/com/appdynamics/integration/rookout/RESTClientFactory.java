/**
 * Copyright 2018. AppDynamics LLC and its affiliates.
 * All Rights Reserved.
 * This is unpublished proprietary source code of AppDynamics LLC and its affiliates.
 * The copyright notice above does not evidence any actual or intended publication of such source code.
 *
 * Simple JAX-RS Client Factory
 * @author aleftik
 * @version 1.0
 */
package com.appdynamics.integration.rookout;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import java.io.IOException;

public class RESTClientFactory {
    private static final RESTClientFactory instance = new RESTClientFactory();
    public static final int TWO_MINUTES = 60 * 1000 * 2;
    public static final int TWENTY_SECONDS = 20 * 1000;
    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static ClientConfig clientConfig;

    static {
        clientConfig = new ClientConfig();
        clientConfig.property(ClientProperties.READ_TIMEOUT, TWO_MINUTES);
        clientConfig.property(ClientProperties.CONNECT_TIMEOUT, TWENTY_SECONDS);
    }

    private RESTClientFactory() {

    }

    public Client getControllerClient(String  apiKey) {
        Client client = ClientBuilder.newClient(clientConfig);
        client.register(new ClientRequestFilter() {
            @Override
            public void filter(ClientRequestContext requestContext) throws IOException {
                requestContext.getHeaders().add(AUTHORIZATION_HEADER,"Bearer " + apiKey);
            }
        });
        return client;
    }


    public static RESTClientFactory getInstance() {
        return instance;
    }
}
