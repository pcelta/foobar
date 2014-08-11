package com.pcelta.foobar.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Status 
{
	// codes
	public static Integer CODE_SUCCESS = 200;
	
	//descriptions
	public static String DESCRIPTION_SUCCESS = "running";
	
	private Integer code;
	private String description;
	private String lastUpdating;
	
	public Integer getCode() {
		return code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLastUpdating() {
		return lastUpdating;
	}
	
	public void setLastUpdating(String lastUpdating) {
		this.lastUpdating = lastUpdating;
	}
	
	public static Status createSuccess()
	{
		Status status = new Status();
		status.code = Status.CODE_SUCCESS;
		status.setDescription(Status.DESCRIPTION_SUCCESS);
		
		return status;
	}
	
	@Override
	public String toString() {
		return "[]";
	}
}
