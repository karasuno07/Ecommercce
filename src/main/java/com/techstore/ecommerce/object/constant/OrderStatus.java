package com.techstore.ecommerce.object.constant;

public enum OrderStatus {

    PENDING("PENDING"),
    PROCESSED("PROCESSED"),
    DELIVERING("DELIVERING"),
    RECEIVED("RECEIVED");

    private final String status;

    public String getStatus() {
        return status;
    }

    OrderStatus(String status) {
        this.status = status;
    }
}
