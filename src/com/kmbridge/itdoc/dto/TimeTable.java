package com.kmbridge.itdoc.dto;

public class TimeTable {
	
	private String weekDay; 
	private String startTime;
	private String endTime;
	private String lunchStartTime;
	private String lunchEndTime;
	public TimeTable(String weekDay, String startTime, String endTime,
			String lunchStartTime, String lunchEndTime) {
		super();
		this.weekDay = weekDay;
		this.startTime = startTime;
		this.endTime = endTime;
		this.lunchStartTime = lunchStartTime;
		this.lunchEndTime = lunchEndTime;
	}
	public TimeTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getLunchStartTime() {
		return lunchStartTime;
	}
	public void setLunchStartTime(String lunchStartTime) {
		this.lunchStartTime = lunchStartTime;
	}
	public String getLunchEndTime() {
		return lunchEndTime;
	}
	public void setLunchEndTime(String lunchEndTime) {
		this.lunchEndTime = lunchEndTime;
	}
	
	@Override
	public String toString() {
		return "TimeTable [weekDay=" + weekDay + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", lunchStartTime=" + lunchStartTime
				+ ", lunchEndTime=" + lunchEndTime + "]";
	}
	
}
