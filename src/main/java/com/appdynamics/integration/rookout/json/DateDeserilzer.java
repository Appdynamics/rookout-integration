/*
 * Copyright 2018. AppDynamics LLC and its affiliates.
 * All Rights Reserved.
 * This is unpublished proprietary source code of AppDynamics LLC and its affiliates.
 * The copyright notice above does not evidence any actual or intended publication of such source code.
 *
 * Annoying deserializer to handle the Date format from the REST API
 * @author aleftik
 * @version 1.0
 */
package com.appdynamics.integration.rookout.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;


import java.lang.reflect.Type;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class DateDeserilzer implements JsonDeserializer<Date> {
    private static final Logger logger = Logger.getLogger(DateDeserilzer.class.getName());

    @Override
    public Date deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String date = element.getAsString();
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy");
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            logger.severe(e.getLocalizedMessage());
            return null;
        }
    }
}