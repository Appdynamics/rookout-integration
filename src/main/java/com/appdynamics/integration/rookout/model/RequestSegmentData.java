/*
 * Copyright 2018. AppDynamics LLC and its affiliates.
 * All Rights Reserved.
 * This is unpublished proprietary source code of AppDynamics LLC and its affiliates.
 * The copyright notice above does not evidence any actual or intended publication of such source code.
 *
 * @version 1.0
 */
package com.appdynamics.integration.rookout.model;

public class RequestSegmentData {
    private boolean firstInChain;
    private long serverStartTime;
    private String callChain;
    private String requestGUID;
    private long localStartTime;
    private String httpSessionID;
    private String deepDivePolicy;
    private boolean exitCallsTruncated;
    private boolean unresolvedCallInCallChain;
    private String threadId;
    private String [] stackTraces;
    private String errorSummary;
    private boolean async;
    private long businessTransactionId;
    private String stallDump;
    private String userExperience;
    private long applicationComponentId;
    private long applicationId;
    private long applicationComponentNodeId;
    private SnapshotExitCall [] snapshotExitCalls;

    public boolean isFirstInChain() {
        return firstInChain;
    }

    public void setFirstInChain(boolean firstInChain) {
        this.firstInChain = firstInChain;
    }

    public long getServerStartTime() {
        return serverStartTime;
    }

    public void setServerStartTime(long serverStartTime) {
        this.serverStartTime = serverStartTime;
    }

    public String getCallChain() {
        return callChain;
    }

    public void setCallChain(String callChain) {
        this.callChain = callChain;
    }

    public String getRequestGUID() {
        return requestGUID;
    }

    public void setRequestGUID(String requestGUID) {
        this.requestGUID = requestGUID;
    }

    public long getLocalStartTime() {
        return localStartTime;
    }

    public void setLocalStartTime(long localStartTime) {
        this.localStartTime = localStartTime;
    }

    public String getHttpSessionID() {
        return httpSessionID;
    }

    public void setHttpSessionID(String httpSessionID) {
        this.httpSessionID = httpSessionID;
    }

    public String getDeepDivePolicy() {
        return deepDivePolicy;
    }

    public void setDeepDivePolicy(String deepDivePolicy) {
        this.deepDivePolicy = deepDivePolicy;
    }

    public boolean isExitCallsTruncated() {
        return exitCallsTruncated;
    }

    public void setExitCallsTruncated(boolean exitCallsTruncated) {
        this.exitCallsTruncated = exitCallsTruncated;
    }

    public boolean isUnresolvedCallInCallChain() {
        return unresolvedCallInCallChain;
    }

    public void setUnresolvedCallInCallChain(boolean unresolvedCallInCallChain) {
        this.unresolvedCallInCallChain = unresolvedCallInCallChain;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String[] getStackTraces() {
        return stackTraces;
    }

    public void setStackTraces(String[] stackTraces) {
        this.stackTraces = stackTraces;
    }

    public String getErrorSummary() {
        return errorSummary;
    }

    public void setErrorSummary(String errorSummary) {
        this.errorSummary = errorSummary;
    }

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public long getBusinessTransactionId() {
        return businessTransactionId;
    }

    public void setBusinessTransactionId(long businessTransactionId) {
        this.businessTransactionId = businessTransactionId;
    }

    public String getStallDump() {
        return stallDump;
    }

    public void setStallDump(String stallDump) {
        this.stallDump = stallDump;
    }

    public String getUserExperience() {
        return userExperience;
    }

    public void setUserExperience(String userExperience) {
        this.userExperience = userExperience;
    }

    public long getApplicationComponentId() {
        return applicationComponentId;
    }

    public void setApplicationComponentId(long applicationComponentId) {
        this.applicationComponentId = applicationComponentId;
    }

    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public long getApplicationComponentNodeId() {
        return applicationComponentNodeId;
    }

    public void setApplicationComponentNodeId(long applicationComponentNodeId) {
        this.applicationComponentNodeId = applicationComponentNodeId;
    }

    public SnapshotExitCall[] getSnapshotExitCalls() {
        return snapshotExitCalls;
    }

    public void setSnapshotExitCalls(SnapshotExitCall[] snapshotExitCalls) {
        this.snapshotExitCalls = snapshotExitCalls;
    }
}
