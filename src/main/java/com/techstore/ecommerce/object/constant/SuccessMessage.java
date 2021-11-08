package com.techstore.ecommerce.object.constant;

public enum SuccessMessage {
    //USER
    FIND_ALL_USER("Get all user successfully"),
    FIND_USER_BY_ID("Found a user with id "),
    FIND_USER_BY_USERNAME("Found a user with username "),
    CREATE_USER("created user Successfully"),
    UPDATE_USER("User updated successfully"),
    ACTIVATE_USER("Activate user successfully"),
    DEACTIVATE_USER("Deactivate user successfully"),

    //CATEGORY
    FIND_ALL_CATEGORY("Get all category successfully"),
    FIND_CATEGORY_BY_ID("Found a category with id "),
    CREATE_CATEGORY("Created category Successfully"),
    UPDATE_CATEGORY("Updated category successfully"),
    DELETE_CATEGORY("Delete category successfully"),

    //BRAND
    FIND_ALL_BRAND("Get all brand successfully"),
    FIND_BRAND_BY_ID("Found a brand with id "),
    CREATE_BRAND("Created brand Successfully"),
    UPDATE_BRAND("Updated brand successfully"),
    DELETE_BRAND("Delete brand successfully"),

    //SUPPLIER
    FIND_ALL_SUPPLIER("Get all supplier successfully"),
    FIND_SUPPLIER_BY_ID("Found a supplier with id "),
    CREATE_SUPPLIER("Created supplier Successfully"),
    UPDATE_SUPPLIER("Updated supplier successfully"),
    DELETE_SUPPLIER("Delete supplier successfully"),

    ;

    private final String message;

    public String getMessage() {
        return message;
    }

    SuccessMessage(String message) {
        this.message = message;
    }
}
