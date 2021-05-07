package com.example.dto;

public class RabbitCustomMessage2 {
    private String msg;
    private int priority;
    private boolean secret;

    protected RabbitCustomMessage2() {
    }

    public RabbitCustomMessage2(String msg, int priority, boolean secret) {
        this.msg = msg;
        this.priority = priority;
        this.secret = secret;
    }

    public String getMsg() {
        return msg;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isSecret() {
        return secret;
    }

    @Override
    public String toString() {
        return "RabbitCustomMessage2{" +
                "msg='" + msg + '\'' +
                ", priority=" + priority +
                ", secret=" + secret +
                '}';
    }
}
