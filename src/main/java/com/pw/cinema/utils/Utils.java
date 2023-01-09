package com.pw.cinema.utils;

import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static Object response(Object result, String description) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", result);
        response.put("message", description);
        response.put("success", true);
        return response;
    }

    public static String getAlphaNumericString(int n) {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

}
