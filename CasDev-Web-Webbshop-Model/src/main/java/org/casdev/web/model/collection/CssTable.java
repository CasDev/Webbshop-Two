package org.casdev.web.model.collection;

import java.util.List;

import org.casdev.web.Constants;
import org.casdev.web.database.Connection;
import org.casdev.web.database.DatabaseType;
import org.casdev.web.database.GetObject;
import org.casdev.web.model.Css;
import org.casdev.web.model.Object;

public class CssTable extends Object {

	private Css[] css;
	private int id;

	public CssTable() {
		this.css = new Css[0];
		this.id = 0;
	}

	public Css[] getCss() {
		return css;
	}

	@Override
	public boolean Load() {

		List<java.lang.Object> list = GetObject.get(new Connection(null,
				Constants.DATABASE, Constants.USERNAME, Constants.PASSWORD),
				"_csspages",
				new DatabaseType[] { new DatabaseType(2, "cssid") },
				" WHERE pageid = ? ", new DatabaseType[] { new DatabaseType(2,
						this.id) }, "ORDER BY cssid ");

		if (list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				Css css = new Css();
				css.setId(Integer.parseInt(list.get(i).toString()));
				boolean loaded = css.Load();

				if (loaded) {
					this.add(css);
				}

			}

			// TODO: cache it all in session (? scope ?)
			// TODO: if cache'd then if changing page, all pages will have same
			// css
			// TODO: this is an working solution instead of loading an an
			// css-file
			// TODO: which element?
			return true;
		} else {

			return false;
		}

	}

	@Override
	public boolean Save() {

		return false;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean add(Css css) {
		int size = this.css.length;
		size = size + 1;
		Css[] list = new Css[size];

		for (int i = 0; i < this.css.length; i++) {

			list[i] = this.css[i];

		}
		list[size - 1] = css;
		this.css = list;

		return true;
	}

	public void clear() {
		css = new Css[0];
	}

	public Css get(int index) {
		return css[index];
	}

	public boolean isEmpty() {

		if (css.length > 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean remove(int index) {

		if (css[index] != null) {
			css[index] = null;
			return true;
		}

		return false;
	}

	public int size() {
		return css.length;
	}

}
