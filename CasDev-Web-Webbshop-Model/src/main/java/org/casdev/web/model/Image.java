package org.casdev.web.model;

import java.util.List;

import org.casdev.web.Constants;
import org.casdev.web.database.Connection;
import org.casdev.web.database.DatabaseType;
import org.casdev.web.database.GetObject;

public class Image extends Object {

	private int id;
	private String path;
	private String extention;
	private int productid;
	private String absolutePath;

	public Image() {
		this.id = 0;
		this.path = "/";
		this.extention = ".jpg";
		this.productid = 0;
		this.absolutePath = this.path + "" + this.extention;
	}

	public int getId() {
		return id;
	}

	public String getExtention() {
		return extention;
	}

	public String getPath() {
		return path;
	}

	public int getProductid() {
		return productid;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setExtention(String extention) {
		this.extention = extention;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	@Override
	public boolean Load() {

		if (this.id > 0) {

			List<java.lang.Object> list = GetObject
					.get(new Connection(null, Constants.DATABASE,
							Constants.USERNAME, Constants.PASSWORD),
							"_picture", new DatabaseType[] {
									new DatabaseType(DatabaseType.STRING,
											"path"),
									new DatabaseType(DatabaseType.STRING,
											"extention"),
									new DatabaseType(DatabaseType.INTEGER,
											"productid") }, " WHERE id = ? ",
							new DatabaseType[] { new DatabaseType(
									DatabaseType.INTEGER, this.id) }, null);

			if (list.size() > 0) {
				this.path = list.get(0).toString();
				this.extention = list.get(1).toString();
				this.productid = Integer.parseInt(list.get(2).toString());
				this.absolutePath = this.path + "" + this.extention;

				return true;
			} else {
				return false;
			}

		} else if (this.productid > 0) {

			List<java.lang.Object> list = GetObject.get(
					new Connection(null, Constants.DATABASE,
							Constants.USERNAME, Constants.PASSWORD),
					"_picture", new DatabaseType[] {
							new DatabaseType(DatabaseType.STRING, "path"),
							new DatabaseType(DatabaseType.STRING, "extention"),
							new DatabaseType(DatabaseType.INTEGER, "id") },
					" WHERE productid = ? ",
					new DatabaseType[] { new DatabaseType(DatabaseType.INTEGER,
							this.productid) }, null);

			if (list.size() > 0) {
				this.path = list.get(0).toString();
				this.extention = list.get(1).toString();
				this.id = Integer.parseInt(list.get(2).toString());
				this.absolutePath = this.path + "" + this.extention;

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
		return false;
	}

}
