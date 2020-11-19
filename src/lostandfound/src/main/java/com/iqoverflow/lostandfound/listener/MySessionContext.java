package com.iqoverflow.lostandfound.listener;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class MySessionContext {
    private static Map<String, HttpSession> myMap = new HashMap<>();
    public static synchronized void addSession(HttpSession session) {
        if (session != null) {
            myMap.put(session.getId(), session);
        }
    }

    public static synchronized void removeSession(HttpSession session) {
        if (session != null) {
            myMap.remove(session.getId());
        }
    }

    public static synchronized HttpSession getSession(String sessionId) {
        return myMap.get(sessionId);
    }
}
