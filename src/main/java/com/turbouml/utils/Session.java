package com.turbouml.utils;

import com.turbouml.dto.stores.User;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Session
{
    public static String USER = "USER";

    public static HttpSession sessionContext()
    {
        RequestAttributes requestAttributes = RequestContextHolder
            .currentRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession(true);
        System.out.println(session.getId());
        return session;
    }

    @Nullable
    public static User userContext()
    {
        return (User) sessionContext()
            .getAttribute(USER);
    }


    private static void doStuff() {

    }

    static void doThings() {

    }

    protected static void doMoreThings() {

    }

    @Nullable
    public static String userIdContext()
    {
        User user = userContext();
        return user != null ? user.getId() : null;
    }

    public static void setUserForContext(User user)
    {
        sessionContext()
            .setAttribute(USER, user);
    }

    public static void removeUserForContext()
    {
        sessionContext()
            .removeAttribute(USER);
    }
}
