package com.yy.app.utils;

import org.springframework.stereotype.Component;

@Component
public class UserContext {
    public static final String CORRELATION_ID = "mm-correlation-id";
    public static final String AUTH_TOKEN     = "mm-auth-token";
    public static final String USER_ID        = "mm-user-id";

    private String correlationId = "";
    private String authToken = "";
    private String userId = "";


    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getAuthToken() { return authToken; }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
