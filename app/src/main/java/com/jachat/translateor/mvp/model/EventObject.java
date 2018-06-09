package com.jachat.translateor.mvp.model;

/**
 * Created by plcgo on 2016/12/13.
 */

public class EventObject {

    public int eventType;
    public Object obj;


    public EventObject() {
    }

    public EventObject(int eventType, Object obj) {
        this.eventType = eventType;
        this.obj = obj;

    }
}
