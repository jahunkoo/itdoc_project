package com.kmbridge.itdoc.dto;

public class ItemTitle implements Title{
	private String itemTitle;
	
	public ItemTitle(String itemTitle) {
		super();
		this.itemTitle = itemTitle;
	}
	public ItemTitle() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getItemTitle() {
		return itemTitle;
	}
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	@Override
	public String toString() {
		return "ItemTitle [itemTitle=" + itemTitle + "]";
	}
	
	@Override
	public boolean isSection() {
		return false;
	}
	
}
