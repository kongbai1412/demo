package com.whb.shangmidemo.event;

public class MessageEvent {
    private EventBusMsgType eventBusMsgType;
    private Object msg;

    public MessageEvent() {
    }

    public MessageEvent(EventBusMsgType eventBusMsgType, Object msg) {
        this.eventBusMsgType = eventBusMsgType;
        this.msg = msg;
    }

    public EventBusMsgType getEventBusMsgType() {
        return eventBusMsgType;
    }

    public void setEventBusMsgType(EventBusMsgType eventBusMsgType) {
        this.eventBusMsgType = eventBusMsgType;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "eventBusMsgType=" + eventBusMsgType +
                ", msg=" + msg +
                '}';
    }
}