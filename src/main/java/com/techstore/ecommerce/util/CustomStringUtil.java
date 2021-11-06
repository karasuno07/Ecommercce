package com.techstore.ecommerce.util;

public final class CustomStringUtil {

    public static String generateSlug(String text) {
        String s = text.replace("[^a-zA-Z0-9\\s]", "");
        return s.toLowerCase().replace("\\s", "-");
    }
}
