package com.vicheak.springdatajpademoproject.util;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResponseSuccess {

    public static Map<String, Object> responseMapping(String message) {
        return new HashMap<>() {{
            put("message", message);
            put("status", true);
            put("timestamp", LocalDateTime.now());
        }};
    }

}
