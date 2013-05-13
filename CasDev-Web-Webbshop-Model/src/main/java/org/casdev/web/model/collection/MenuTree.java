package org.casdev.web.model.collection;

import java.util.List;

import org.casdev.web.Constants;
import org.casdev.web.database.Connection;
import org.casdev.web.database.DatabaseType;
import org.casdev.web.database.GetObject;
import org.casdev.web.model.MenuItem;
import org.casdev.web.model.Object;

public class MenuTree extends Object {

	private MenuItem[] menu;
	private byte level;

	public MenuTree() {
		this.menu = new MenuItem[0];
		this.level = 0;
	}

	public MenuItem[] getMenu() {
		return menu;
	}

	public void setLevel(byte level) {
		this.level = level;
	}

	public boolean add(MenuItem menu) {
		int size = this.menu.length;
		size = size + 1;
		MenuItem[] list = new MenuItem[size];

		for (int i = 0; i < this.menu.length; i++) {

			list[i] = this.menu[i];

		}
		list[size - 1] = menu;
		this.menu = list;

		return true;
	}

	public void clear() {
		menu = new MenuItem[0];
	}

	public MenuItem get(int index) {
		return menu[index];
	}

	public boolean isEmpty() {

		if (menu.length > 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean remove(int index) {

		if (menu[index] != null) {
			menu[index] = null;
			return true;
		}

		return false;
	}

	public int size() {
		return menu.length;
	}

	@Override
	public boolean Load() {

		List<java.lang.Object> list = GetObject.get(new Connection(null,
				Constants.DATABASE, Constants.USERNAME, Constants.PASSWORD),
				"_pages", new DatabaseType[] { new DatabaseType(2, "id"),
						new DatabaseType(1, "url"),
						new DatabaseType(1, "title"),
						new DatabaseType(6, "menulevel") },
				" WHERE menu = ? AND menulevel = ? ", new DatabaseType[] {
						new DatabaseType(5, true),
						new DatabaseType(6, this.level) }, null);

		this.clear();
		if (list.size() > 0) {

			for (int i = 0; i < list.size(); i = i + 4) {

				int n = 0;
				MenuItem menu = new MenuItem();
				menu.setId(Integer.parseInt(list.get(i).toString()));
				n = i + 1;
				menu.setUrl(list.get(n).toString());
				n = n + 1;
				menu.setTitle(list.get(n).toString());
				n = n + 1;
				menu.setLevel(Byte.parseByte(list.get(n).toString()));

				this.add(menu);
			}

		} else {
			return false;
		}

		return true;
	}

	@Override
	public boolean Save() {
		return false;
	}

}
