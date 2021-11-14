package com.example.mylistview;

public class Pokemon {
    private String name;
    private int power;
    private String type;
    private int pid;

    public Pokemon(String name, int pid) {
        this.name = name;
        this.pid = pid;
    }

    public Pokemon(String name, int power, String type) {
        this.name = name;
        this.power = power;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
