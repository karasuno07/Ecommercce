package com.techstore.ecommerce.object.constant;

public enum SuccessMessage {
    // USER
    FIND_ALL_USERS("Get all users successfully"),
    FIND_USER_BY_ID("Found a user with id "),
    FIND_USER_BY_USERNAME("Found a user with username "),
    CREATE_USER("Created user Successfully"),
    UPDATE_USER("User updated successfully"),
    ACTIVATE_USER("Activate user successfully"),
    DEACTIVATE_USER("Deactivate user successfully"),

    // ROLE
    FIND_ALL_ROLES("Get all roles successfully"),
    FIND_ROLE_BY_ID("Found a role with id "),
    CREATE_ROLE("Created role Successfully"),
    UPDATE_ROLE("Updated user Successfully"),
    DELETE_ROLE("Deleted user Successfully"),

    // PRODUCT
    FIND_ALL_PRODUCT("Get all products successfully"),
    FIND_PRODUCT_BY_ID("Found a product with id "),
    CREATE_PRODUCT("Created product Successfully"),
    UPDATE_PRODUCT("Updated product successfully"),
    ACTIVATE_PRODUCT("Activate product successfully"),
    DEACTIVATE_PRODUCT("Deactivate product successfully"),


    // CATEGORY
    FIND_ALL_CATEGORIES("Get all categories successfully"),
    FIND_CATEGORY_BY_ID("Found a category with id "),
    CREATE_CATEGORY("Created category Successfully"),
    UPDATE_CATEGORY("Updated category successfully"),
    DELETE_CATEGORY("Delete category successfully"),

    // BRAND
    FIND_ALL_BRANDS("Get all brands successfully"),
    FIND_BRAND_BY_ID("Found a brand with id "),
    CREATE_BRAND("Created brand Successfully"),
    UPDATE_BRAND("Updated brand successfully"),
    DELETE_BRAND("Delete brand successfully"),

    // SUPPLIER
    FIND_ALL_SUPPLIERS("Get all suppliers successfully"),
    FIND_SUPPLIER_BY_ID("Found a supplier with id "),
    CREATE_SUPPLIER("Created supplier Successfully"),
    UPDATE_SUPPLIER("Updated supplier successfully"),
    DELETE_SUPPLIER("Delete supplier successfully"),

    //ORDER
    FIND_ALL_ORDERS("Get all orders successfully"),
    FIND_ORDER_BY_ID("Found a order with id "),
    CREATE_ORDER("Created order Successfully"),
    DELETE_ORDER("Delete order successfully"),
    UPDATE_STATUS("Updated status ")

    ;

    private final String message;

    public String getMessage() {
        return message;
    }

    SuccessMessage(String message) {
        this.message = message;
    }
}
