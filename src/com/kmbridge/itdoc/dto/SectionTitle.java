package com.kmbridge.itdoc.dto;

public class SectionTitle implements Title{
	private String sectionTitle;

	public SectionTitle() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SectionTitle(String sectionTitle) {
		super();
		this.sectionTitle = sectionTitle;
	}
	
	public String getSectionTitle() {
		return sectionTitle;
	}
	public void setSectionTitle(String sectionTitle) {
		this.sectionTitle = sectionTitle;
	}

	@Override
	public String toString() {
		return "SectionTitle [sectionTitle=" + sectionTitle + "]";
	}
	@Override
	public boolean isSection() {
		return true;
	}
	
}
