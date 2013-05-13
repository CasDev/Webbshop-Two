package org.casdev.web.model;

import java.util.ArrayList;
import java.util.List;

import org.casdev.web.Constants;
import org.casdev.web.database.Connection;
import org.casdev.web.database.DatabaseType;
import org.casdev.web.database.GetObject;
import org.casdev.web.database.SaveObject;
import org.casdev.web.database.UpdateObject;
import org.casdev.web.model.attribute.PageType;

public class MenuItem extends Object {

	private String url;
	private byte level;
	private int id;
	private String title;
	private PageType type;

	public MenuItem() {
		this.url = "";
		this.level = 0;
		this.id = 0;
		this.title = "";
		this.type = PageType.NONE;
	}

	public int getId() {
		return id;
	}
	
	public PageType getType() {
		return type;
	}

	public byte getLevel() {
		return level;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public void setLevel(byte level) {
		this.level = level;
	}
	
	public void setType(PageType type) {
		this.type = type;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public boolean Load() {

		List<java.lang.Object> list = new ArrayList<java.lang.Object>();

		if (this.id > 0) {

			list = GetObject.get(new Connection(null, Constants.DATABASE,
					Constants.USERNAME, Constants.PASSWORD), "_pages",
					new DatabaseType[] { new DatabaseType(1, "url"),
							new DatabaseType(1, "title"),
							new DatabaseType(6, "menulevel") },
					" WHERE menu = ? AND id = ? ", new DatabaseType[] {
							new DatabaseType(5, true),
							new DatabaseType(2, this.id) }, null);

		} else {

			list = GetObject.get(new Connection(null, Constants.DATABASE,
					Constants.USERNAME, Constants.PASSWORD), "_pages",
					new DatabaseType[] { new DatabaseType(1, "url"),
							new DatabaseType(1, "title"),
							new DatabaseType(6, "menulevel") },
					" WHERE menu = ? AND type = ? ", new DatabaseType[] {
							new DatabaseType(5, true),
							new DatabaseType(1, this.type.toString()) }, null);

		}

		if (list.size() > 0) {

			this.url = list.get(0).toString();
			this.title = list.get(1).toString();
			this.level = Byte.parseByte(list.get(2).toString());

			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean Save() {

		if (this.id > 0) {

			boolean isTrue = UpdateObject.update(
					new Connection(null, Constants.DATABASE,
							Constants.USERNAME, Constants.PASSWORD),
					new String[] { "url", "title", "menulevel" },
					new DatabaseType[] { new DatabaseType(1, this.url),
							new DatabaseType(1, this.title),
							new DatabaseType(6, this.level) }, "_pages",
					" WHERE id = ? AND menu = ?", new DatabaseType[] {
							new DatabaseType(2, this.id),
							new DatabaseType(5, true) });

			return isTrue;

		} else {
			int id = SaveObject.save(new Connection(null, Constants.DATABASE,
					Constants.USERNAME, Constants.PASSWORD), new String[] {
					"url", "title", "menulevel" }, "_pages",
					new DatabaseType[] { new DatabaseType(1, this.url),
							new DatabaseType(1, this.title),
							new DatabaseType(6, this.level) });

			if (id > 0) {
				this.id = id;

				return true;
			}

		}

		return false;
	}
}
