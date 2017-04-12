package com.gdin.analyse.entity;

public class ClassEntity {

    public int loginClassId;
    public String className;

    public ClassEntity(int loginClassId, String className) {
        this.loginClassId = loginClassId;
        this.className = className;
    }

    public void setLoginClassId(int loginClassId) {
        this.loginClassId = loginClassId;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getLoginClassId() {
        return loginClassId;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return "ClassEntity{" +
                "loginClassId=" + loginClassId +
                ", className='" + className + '\'' +
                '}';
    }
}
