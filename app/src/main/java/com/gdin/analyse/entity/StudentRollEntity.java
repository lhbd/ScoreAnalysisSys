package com.gdin.analyse.entity;

public class StudentRollEntity {
    private int id;
    private String name;
    private String score;
    private String ch;
    private String math;
    private String en;
    private String gRank;
    private String cRank;

    public StudentRollEntity() {
    }

    public StudentRollEntity(int id, String name, String score, String ch, String math, String en, String gRank, String cRank) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.ch = ch;
        this.math = math;
        this.en = en;
        this.gRank = gRank;
        this.cRank = cRank;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }

    public String getCh() {
        return ch;
    }

    public String getMath() {
        return math;
    }

    public String getEn() {
        return en;
    }

    public String getgRank() {
        return gRank;
    }

    public String getcRank() {
        return cRank;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public void setMath(String math) {
        this.math = math;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public void setgRank(String gRank) {
        this.gRank = gRank;
    }

    public void setcRank(String cRank) {
        this.cRank = cRank;
    }
}
