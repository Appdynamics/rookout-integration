/*
 * Copyright 2018. AppDynamics LLC and its affiliates.
 * All Rights Reserved.
 * This is unpublished proprietary source code of AppDynamics LLC and its affiliates.
 * The copyright notice above does not evidence any actual or intended publication of such source code.
 *
 *  Handles the alert request that contains the template
 * @author aleftik
 * @version 1.0
 */
package com.appdynamics.integration.rookout;

import com.appdynamics.integration.rookout.json.DateDeserilzer;
import com.appdynamics.integration.rookout.model.AlertInfo;
import com.appdynamics.integration.rookout.model.RequestSegmentData;
import com.appdynamics.integration.rookout.model.Snapshot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;


public class WebhookHandler extends AbstractHandler {
    public static final int DEFAULT_TIME_WINDOW_FOR_SNAPSHOT = 5;
    private Properties props;
    private Gson gson = null;

    public WebhookHandler(Properties props) {
        this.props = props;
        this.gson = new GsonBuilder().registerTypeAdapter(Date.class,new DateDeserilzer()).create();
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        validateRequest(request, response);
        AlertInfo alertInfo = getAlertInfo(request);
        Snapshot snapshot = getSnapshot(alertInfo);
    }

    private Snapshot getSnapshot(AlertInfo alert) {
        String apiKey = props.getProperty("controller.api.key");
        getTimeWindow();
        Client client = RESTClientFactory.getInstance().getControllerClient(apiKey);
        Response response = client.target(alert.getControllerUrl()).path("rest").path("applications").path(alert.getApplicationName()).path("request-snapshots")
                .queryParam("output","JSON")
                .queryParam("need-exit-calls",true)
                .queryParam("time-range-type","BETWEEN_TIMES")
                .queryParam("start-time",alert.getEventTime().getTime() - (getTimeWindow()*1000*60))
                .queryParam("end-time", alert.getEventTime().getTime()+ (getTimeWindow()*1000*60))
                .queryParam("guids",alert.getGuid())
                .request().buildGet().invoke();
        String json = response.readEntity(String.class);
        System.out.println(json);
        return buildSnapshotFromJSON(json);
    }

    private Snapshot buildSnapshotFromJSON(String json) {
        JsonArray array = gson.fromJson(json, JsonArray.class);
        Iterator<JsonElement> rsds = array.iterator();
        Snapshot snapshot = new Snapshot();
        while (rsds.hasNext()) {
            JsonElement rsdElement = rsds.next();
            RequestSegmentData rsd = gson.fromJson(rsdElement,RequestSegmentData.class);
            snapshot.addRequestSegmentData(rsd);

        }
        return snapshot;
    }

    private int getTimeWindow() {
        String timeWindowStr = props.getProperty("snapshot.time.window");
        int timeWindow = DEFAULT_TIME_WINDOW_FOR_SNAPSHOT;
        if (timeWindowStr!=null) {
            try {timeWindow = Integer.parseInt(timeWindowStr);} catch (NumberFormatException nfe) {}
        }
        return timeWindow;
    }

    private AlertInfo getAlertInfo(HttpServletRequest request) throws IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();

        AlertInfo alertInfo = gson.fromJson(data,AlertInfo.class);
        return alertInfo;
    }

    private void validateRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getContentType() != null) {
            if (!request.getContentType().startsWith(MediaType.APPLICATION_JSON)) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("only accepts application/jsonpayloads");
            }
        }
    }
}
