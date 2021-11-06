package com.techstore.ecommercce.object.constant;

public enum Authorities {

    PRODUCT_READ("product_read"),
    PRODUCT_CREATE("product_create"),
    PRODUCT_UPDATE("product_update"),
    PRODUCT_DELETE("product_delete");

    private final String permission;

    public String getPermission() {
        return permission;
    }

    Authorities(String permission) {
        this.permission = permission;
    }
}
