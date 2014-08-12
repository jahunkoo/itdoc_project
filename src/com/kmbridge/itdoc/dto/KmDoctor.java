package com.kmbridge.itdoc.dto;

public class KmDoctor {
	
	private int id;
	private int kmClinicId;
	private String name;
	private String major;
	private String career;
	private String academy;
	private String introduce;
	
	public KmDoctor(int id, int kmClinicId, String name, String major,
			String career, String academy, String introduce) {
		super();
		this.id = id;
		this.kmClinicId = kmClinicId;
		this.name = name;
		this.major = major;
		this.career = career;
		this.academy = academy;
		this.introduce = introduce;
	}
	public KmDoctor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getKmClinicId() {
		return kmClinicId;
	}
	public void setKmClinicId(int kmClinicId) {
		this.kmClinicId = kmClinicId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getAcademy() {
		return academy;
	}
	public void setAcademy(String academy) {
		this.academy = academy;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	@Override
	public String toString() {
		return "KmDoctor [id=" + id + ", kmClinicId=" + kmClinicId + ", name="
				+ name + ", major=" + major + ", career=" + career
				+ ", academy=" + academy + ", introduce=" + introduce + "]";
	}
	
}
