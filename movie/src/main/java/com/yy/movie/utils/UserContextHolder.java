package com.yy.movie.utils;


import org.springframework.util.Assert;

public class UserContextHolder {
    private static final ThreadLocal<UserContext>
            userContext = new ThreadLocal<>();

    public static final UserContext getContext() {
        if(userContext.get() == null) userContext.set(new UserContext());
        return userContext.get();
    }

    public static final void setContext(UserContext context) {
        Assert.notNull(context,
                "Only non-null UserContext instances are permitted");
        userContext.set(context);
    }
}
