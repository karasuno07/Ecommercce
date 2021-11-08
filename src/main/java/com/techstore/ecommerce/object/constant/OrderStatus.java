package com.techstore.ecommerce.object.constant;

public enum OrderStatus {

    PENDING("PENDING"),
    PROCESSED("PROCESSED"),
    DELIVERING("DELIVERING"),
    RECEIVED("RECEIVED"),
    DECLINED("DECLINED");

    private final String status;

    public String getStatus() {
        return status;
    }

    OrderStatus(String status) {
        this.status = status;
    }
}
