package com.example.onestep.calendar;

public class DayInfo
{
    private String day;
    private boolean inMonth;
     
    /**
     * ��¥�� ��ȯ�Ѵ�.
     * 
     * @return day ��¥
     */
    public String getDay()
    {
        return day;
    }
 
    /**
     * ��¥�� �����Ѵ�.
     * 
     * @param day ��¥
     */
    public void setDay(String day)
    {
        this.day = day;
    }
 
    /**
     * �̹����� ��¥���� ������ ��ȯ�Ѵ�.
     * 
     * @return inMonth(true/false)
     */
    public boolean isInMonth()
    {
        return inMonth;
    }
 
    /**
     * �̹����� ��¥���� ������ �����Ѵ�.
     * 
     * @param inMonth(true/false)
     */
    public void setInMonth(boolean inMonth)
    {
        this.inMonth = inMonth;
    }
 
}