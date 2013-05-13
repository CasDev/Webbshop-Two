package org.casdev.web.model;

public class Attribute {
	
	private String attribute;
	private String value;
	
	public Attribute() {
		attribute = "";
		value = "";
	}
	
	public String getAttribute() {
		return attribute;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

}
