package org.casdev.web.model;

import java.util.List;

import org.casdev.web.Constants;
import org.casdev.web.database.Connection;
import org.casdev.web.database.DatabaseType;
import org.casdev.web.database.GetObject;

public class Css extends Object {

	private String attribute;
	private String value;
	private int id;

	public Css() {
		this.id = 0;
		this.value = "";
		this.attribute = "";
	}

	@Override
	public boolean Load() {

		if (this.id > 0) {

			List<java.lang.Object> list = GetObject.get(
					new Connection(null, Constants.DATABASE,
							Constants.USERNAME, Constants.PASSWORD), "_css",
					new DatabaseType[] { new DatabaseType(1, "attribute"),
							new DatabaseType(1, "value") }, " WHERE id = ? ",
					new DatabaseType[] { new DatabaseType(2, this.id) }, null);
			
			if (list.size() > 0) {
				
				this.attribute = list.get(0).toString();
				this.value = list.get(1).toString();
				
				return true;
			} else {
				
				return false;
			}

		} else {

			return false;
		}

	}

	@Override
	public boolean Save() {
		// TODO Auto-generated method stub
		return super.Save();
	}

	public String getAttribute() {
		return attribute;
	}
	
	public int getId() {
		return id;
	}

	public String getValue() {
		return value;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
