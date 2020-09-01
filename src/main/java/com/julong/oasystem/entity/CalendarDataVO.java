package com.julong.oasystem.entity;

/**
 * @Author Taltoo
 * @Date 2020/8/28 0028 上午 9:47
 * @Description：
 */
public class CalendarDataVO {
    private String id;
    private String years;
    private String months;
    private String days;
    private String things;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getThings() {
        return things;
    }

    public void setThings(String things) {
        this.things = things;
    }

    @Override
    public String toString() {
        return "CalendarDataVO{" +
                "id='" + id + '\'' +
                ", years='" + years + '\'' +
                ", months='" + months + '\'' +
                ", days='" + days + '\'' +
                ", things='" + things + '\'' +
                '}';
    }
}
