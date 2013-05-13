package org.casdev.web.model.collection;

import java.util.ArrayList;
import java.util.List;

import org.casdev.web.Constants;
import org.casdev.web.database.Connection;
import org.casdev.web.database.DatabaseType;
import org.casdev.web.database.DeleteObject;
import org.casdev.web.database.GetObject;
import org.casdev.web.database.SaveObject;
import org.casdev.web.model.Object;

public class ScriptCollection extends Object {

	private List<String> scripts;
	private int id;

	public ScriptCollection() {
		this.scripts = new ArrayList<String>();
		this.id = 0;
	}

	public List<String> getScripts() {
		return scripts;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public boolean Load() {

		if (id > 0) {

			List<java.lang.Object> list = GetObject.get(
					new Connection(null, Constants.DATABASE,
							Constants.USERNAME, Constants.PASSWORD),
					"_scripts", new DatabaseType[] { new DatabaseType(1,
							"script") }, " WHERE id = ? OR id = ?",
					new DatabaseType[] { new DatabaseType(2, this.id),
							new DatabaseType(2, 0) }, null);

			if (list.size() > 0) {

				this.scripts = new ArrayList<String>();
				for (java.lang.Object object : list) {

					this.scripts.add(object.toString());

				}
				return true;

			}

		}

		return false;
	}

	@Override
	public boolean Save() {

		DeleteObject.delete(new Connection(null, Constants.DATABASE,
				Constants.USERNAME, Constants.PASSWORD), "_scripts",
				" WHERE id = ? ", new DatabaseType[] { new DatabaseType(2,
						this.id) });

		for (String script : this.scripts) {

			int i = SaveObject
					.save(new Connection(null, Constants.DATABASE,
							Constants.USERNAME, Constants.PASSWORD),
							new String[] { "id", "script" }, "_scripts",
							new DatabaseType[] { new DatabaseType(2, this.id),
									new DatabaseType(1, script) });
		}

		return false;
	}

	public boolean add(String script) {
		return scripts.add(script);
	}

	public void clear() {
		scripts.clear();
	}

	public String get(int index) {
		return scripts.get(index);
	}

	public boolean isEmpty() {

		if (scripts.size() > 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean remove(int index) {

		if (scripts.get(index) != null) {

			scripts.remove(index);
			return true;

		}

		return false;
	}

	public int size() {
		return scripts.size();
	}

}
