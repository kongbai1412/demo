package com.whb.shangmidemo.entity;

public class BusinessFirstBean {

    private String title;

    private String money;

    private Float numerical;

    public BusinessFirstBean(String title, String money, Float numerical) {
        this.title = title;
        this.money = money;
        this.numerical = numerical;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Float getNumerical() {
        return numerical;
    }

    public void setNumerical(Float numerical) {
        this.numerical = numerical;
    }
}
