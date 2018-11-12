/**
 * Copyright 2018. AppDynamics LLC and its affiliates.
 * All Rights Reserved.
 * This is unpublished proprietary source code of AppDynamics LLC and its affiliates.
 * The copyright notice above does not evidence any actual or intended publication of such source code.
 *
 * @author aleftik
 * @version 1.0
 */

package com.appdynamics.integration.rookout.model;

import java.util.ArrayList;
import java.util.List;

public class Snapshot {
    private final List<RequestSegmentData> rsds = new ArrayList<RequestSegmentData>();


    public List<RequestSegmentData> getSegments() {
        return rsds;
    }

    public void addRequestSegmentData(RequestSegmentData rsd) {
        rsds.add(rsd);
    }
}
