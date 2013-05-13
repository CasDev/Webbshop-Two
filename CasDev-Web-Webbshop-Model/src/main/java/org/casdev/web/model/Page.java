package org.casdev.web.model;

import java.util.List;

import org.casdev.web.Constants;
import org.casdev.web.database.Connection;
import org.casdev.web.database.DatabaseType;
import org.casdev.web.database.GetObject;
import org.casdev.web.database.SaveObject;
import org.casdev.web.database.UpdateObject;
import org.casdev.web.model.attribute.PageType;
import org.casdev.web.model.collection.ScriptCollection;

public class Page extends Object {

	private String url;
	private PageType type;
	private String title;
	private Integer id;
	private ScriptCollection scripts;

	public Page() {
		this.url = "";
		this.type = PageType.NONE;
		this.id = 0;
		this.title = "";
		this.scripts = new ScriptCollection();
		this.scripts.setId(this.id);
	}

	public ScriptCollection getScripts() {
		return scripts;
	}

	public String getTitle() {
		return title;
	}

	public Integer getId() {
		return id;
	}

	public PageType getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public void setId(Integer id) {
		this.id = id;
		this.scripts.setId(id);
	}

	public void setType(PageType type) {
		this.type = type;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public boolean Load() {
		if (id <= 0) {
			List<java.lang.Object> list = GetObject.get(
					new Connection(null, Constants.DATABASE,
							Constants.USERNAME, Constants.PASSWORD),
					"_pages",
					new DatabaseType[] { new DatabaseType(1, "url"),
							new DatabaseType(1, "type"),
							new DatabaseType(2, "id"),
							new DatabaseType(1, "title") },
					" WHERE type = ? ",
					new DatabaseType[] { new DatabaseType(1, this.type
							.toString()) }, null);

			if (list.size() > 0) {

				this.url = list.get(0).toString();
				this.type = PageType.valueOf(list.get(1).toString());
				this.id = Integer.parseInt(list.get(2).toString());
				this.scripts.setId(this.id);
				this.title = list.get(3).toString();

				return true;
			} else {
				return false;
			}
		} else {

			List<java.lang.Object> list = GetObject.get(
					new Connection(null, Constants.DATABASE,
							Constants.USERNAME, Constants.PASSWORD), "_pages",
					new DatabaseType[] { new DatabaseType(1, "url"),
							new DatabaseType(1, "type"),
							new DatabaseType(1, "title") }, " WHERE id = ? ",
					new DatabaseType[] { new DatabaseType(2, this.id) }, null);

			if (list.size() > 0) {

				this.url = list.get(0).toString();
				this.type = PageType.valueOf(list.get(1).toString());
				this.title = list.get(2).toString();

				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public boolean Save() {

		if (this.id == 0) {

			int id = SaveObject.save(new Connection(null, Constants.DATABASE,
					Constants.USERNAME, Constants.PASSWORD), new String[] {
					"url", "type", "title" }, "_pages", new DatabaseType[] {
					new DatabaseType(1, this.url),
					new DatabaseType(1, this.type.toString()),
					new DatabaseType(1, this.title) });

			if (id > 0) {
				this.id = id;

				return true;
			} else {
				return false;
			}

		} else {

			boolean isTrue;

			isTrue = UpdateObject.update(
					new Connection(null, Constants.DATABASE,
							Constants.USERNAME, Constants.PASSWORD),
					new String[] { "url", "type", "title" },
					new DatabaseType[] { new DatabaseType(1, this.url),
							new DatabaseType(1, this.type.toString()),
							new DatabaseType(1, this.title) }, "_pages",
					" WHERE id = ? ", new DatabaseType[] { new DatabaseType(2,
							this.id) });

			return isTrue;

		}

	}
}
