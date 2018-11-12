/**
 * Copyright 2018. AppDynamics LLC and its affiliates.
 * All Rights Reserved.
 * This is unpublished proprietary source code of AppDynamics LLC and its affiliates.
 * The copyright notice above does not evidence any actual or intended publication of such source code.
 *
 *
 * SnapshotExitCall contains the exit RSD
 * @author aleftik
 * @version 1.0
 */
package com.appdynamics.integration.rookout.model;

public class SnapshotExitCall {
    private long timeTakenInMillis;
    private long count;
    private String exitPointName;
    private String snapshotSequenceCounter;
    private String detailString;
    private int errorCount;
    private String callingMethod;
    private String errorDetails;
    private long customExitPointId;
    private long timestamp;
    private String toComponentId;


    public long getTimeTakenInMillis() {
        return timeTakenInMillis;
    }

    public void setTimeTakenInMillis(long timeTakenInMillis) {
        this.timeTakenInMillis = timeTakenInMillis;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getExitPointName() {
        return exitPointName;
    }

    public void setExitPointName(String exitPointName) {
        this.exitPointName = exitPointName;
    }

    public String getSnapshotSequenceCounter() {
        return snapshotSequenceCounter;
    }

    public void setSnapshotSequenceCounter(String snapshotSequenceCounter) {
        this.snapshotSequenceCounter = snapshotSequenceCounter;
    }

    public String getDetailString() {
        return detailString;
    }

    public void setDetailString(String detailString) {
        this.detailString = detailString;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    public String getCallingMethod() {
        return callingMethod;
    }

    public void setCallingMethod(String callingMethod) {
        this.callingMethod = callingMethod;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }

    public long getCustomExitPointId() {
        return customExitPointId;
    }

    public void setCustomExitPointId(long customExitPointId) {
        this.customExitPointId = customExitPointId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getToComponentId() {
        return toComponentId;
    }

    public void setToComponentId(String toComponentId) {
        this.toComponentId = toComponentId;
    }
}
