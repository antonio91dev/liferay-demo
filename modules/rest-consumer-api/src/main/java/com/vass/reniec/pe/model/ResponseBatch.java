package com.vass.reniec.pe.model;

public class ResponseBatch {
    private String className;
    private String contentType;
    private String errorMessage;
    private String executeStatus;
    private String externalReferenceCode;
    private int id;
    private String importStrategy;
    private String operation;
    private int processedItemsCount;
    private String startTime;
    private int totalItemsCount;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getExecuteStatus() {
        return executeStatus;
    }

    public void setExecuteStatus(String executeStatus) {
        this.executeStatus = executeStatus;
    }

    public String getExternalReferenceCode() {
        return externalReferenceCode;
    }

    public void setExternalReferenceCode(String externalReferenceCode) {
        this.externalReferenceCode = externalReferenceCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImportStrategy() {
        return importStrategy;
    }

    public void setImportStrategy(String importStrategy) {
        this.importStrategy = importStrategy;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getProcessedItemsCount() {
        return processedItemsCount;
    }

    public void setProcessedItemsCount(int processedItemsCount) {
        this.processedItemsCount = processedItemsCount;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getTotalItemsCount() {
        return totalItemsCount;
    }

    public void setTotalItemsCount(int totalItemsCount) {
        this.totalItemsCount = totalItemsCount;
    }
}

