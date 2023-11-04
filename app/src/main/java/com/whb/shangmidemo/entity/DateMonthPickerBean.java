package com.whb.shangmidemo.entity;

import java.util.List;

public class DateMonthPickerBean {

    private Integer year;

    private Integer month;

    private String monthStr;

    private List<DateDayPickerBean> dateDayPickerBean;

    public DateMonthPickerBean() {
    }

    public DateMonthPickerBean(Integer year, Integer month, String monthStr, List<DateDayPickerBean> dateDayPickerBean) {
        this.year = year;
        this.month = month;
        this.monthStr = monthStr;
        this.dateDayPickerBean = dateDayPickerBean;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getMonthStr() {
        return monthStr;
    }

    public void setMonthStr(String monthStr) {
        this.monthStr = monthStr;
    }

    public List<DateDayPickerBean> getDateDayPickerBean() {
        return dateDayPickerBean;
    }

    public void setDateDayPickerBean(List<DateDayPickerBean> dateDayPickerBean) {
        this.dateDayPickerBean = dateDayPickerBean;
    }
}
