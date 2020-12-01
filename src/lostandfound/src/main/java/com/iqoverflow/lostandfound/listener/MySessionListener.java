package com.iqoverflow.lostandfound.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class MySessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {

        MySessionContext.addSession(se.getSession());

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        MySessionContext.removeSession(se.getSession());
    }

    public void sessionCreate(HttpSessionEvent sessionEvent) {
        MySessionContext.addSession(sessionEvent.getSession());
    }

    public void sessionDestroy(HttpSessionEvent sessionEvent) {
        MySessionContext.removeSession(sessionEvent.getSession());
    }
}
