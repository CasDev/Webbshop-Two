package org.casdev.web.model;

import java.util.List;

import org.casdev.web.Constants;
import org.casdev.web.database.Connection;
import org.casdev.web.database.DatabaseType;
import org.casdev.web.database.GetObject;

public class Application extends Object {

	private boolean logo;
	private String logoUrl;
	private String applicationName;
	private long id;
	private String home;

	public Application() {
		this.logo = false;
		this.logoUrl = "/";
		this.applicationName = "";
		this.id = 0;
		this.home = "/		";
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public boolean isLogo() {
		return logo;
	}

	public String getHome() {
		return home;
	}

	@Override
	public boolean Load() {

		if (this.id > 0) {
			List<java.lang.Object> list = GetObject.get(
					new Connection(null, Constants.DATABASE,
							Constants.USERNAME, Constants.PASSWORD),
					"_application", new DatabaseType[] {
							new DatabaseType(1, "applicationName"),
							new DatabaseType(1, "logoUrl"),
							new DatabaseType(5, "logo"),
							new DatabaseType(1, "home") }, " WHERE id = ? ",
					new DatabaseType[] { new DatabaseType(2, this.id) }, null);

			if (list.size() > 0) {
				this.applicationName = list.get(0).toString();
				this.logoUrl = list.get(1).toString();
				this.logo = Boolean.parseBoolean(list.get(2).toString());
				this.home = list.get(3).toString();
			} else {
				return false;
			}

			return true;
		}

		return false;

	}

	@Override
	public boolean Save() {
		return false;
	}

}