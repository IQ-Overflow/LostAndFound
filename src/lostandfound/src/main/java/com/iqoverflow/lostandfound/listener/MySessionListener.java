package com.iqoverflow.lostandfound.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class MySessionListener implements HttpSessionListener {
    public void sessionCreate(HttpSessionEvent sessionEvent) {
        MySessionContext.addSession(sessionEvent.getSession());
    }

    public void sessionDestroy(HttpSessionEvent sessionEvent) {
        MySessionContext.removeSession(sessionEvent.getSession());
    }
}
