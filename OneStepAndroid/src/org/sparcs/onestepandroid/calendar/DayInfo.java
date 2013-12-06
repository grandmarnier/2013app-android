package org.sparcs.onestepandroid.calendar;

import java.util.ArrayList;

public class DayInfo
{
    public ArrayList<EventInfo> getEvents() {
		return events;
	}

	public DayInfo() {
		super();
		this.events = new ArrayList<EventInfo>();
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	private String day="1";
    private boolean inMonth=true;
    private int year=1;
    private int month=1;
    private String test=null;
    private ArrayList<EventInfo> events;
     
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