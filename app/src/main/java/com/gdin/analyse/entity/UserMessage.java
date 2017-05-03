package com.gdin.analyse.entity;


public class UserMessage {
    private String string;
    private String value;

    public UserMessage(String string, String value) {
        this.string = string;
        this.value = value;
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "string='" + string + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String getString() {
        return string;
    }

    public String getValue() {
        return value;
    }

    public void setString(String string) {
        this.string = string;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
