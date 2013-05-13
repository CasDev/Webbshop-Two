package org.casdev.web.model.collection;

import java.util.List;

import org.casdev.web.Constants;
import org.casdev.web.database.Connection;
import org.casdev.web.database.DatabaseType;
import org.casdev.web.database.DeleteObject;
import org.casdev.web.database.GetObject;
import org.casdev.web.database.SaveObject;
import org.casdev.web.model.Attribute;
import org.casdev.web.model.Object;

public class AttributeCollection extends Object {

	private Attribute[] attributes;
	private int[] id;

	public AttributeCollection() {
		this.attributes = new Attribute[0];
		this.id = new int[0];
	}
	
	public Attribute[] getAttributes() {
		return attributes;
	}

	@Override
	public boolean Load() {

		if (id.length > 0) {
			
			this.clear();
			for (int x = 0; x < id.length; x++) {

				List<java.lang.Object> list = GetObject.get(new Connection(
						null, Constants.DATABASE, Constants.USERNAME,
						Constants.PASSWORD), "_attributes", new DatabaseType[] {
						new DatabaseType(1, "attribute"),
						new DatabaseType(1, "value") }, " WHERE id = ? ",
						new DatabaseType[] { new DatabaseType(2, this.id[x]) },
						null);
				
				if (list.size() > 0) {
					for (int i = 0; i < list.size(); i = i + 2) {

						Attribute attribute = new Attribute();
						attribute.setAttribute(list.get(i).toString());
						int j = i + 1;
						attribute.setValue(list.get(j).toString());

						this.add(attribute);

					}

				} else {
					return false;
				}

			}

		} else {

			return false;
		}
		
		return true;
	}

	@Override
	public boolean Save() {

		DeleteObject.delete(new Connection(null, Constants.DATABASE,
				Constants.USERNAME, Constants.PASSWORD), "_attributes",
				" WHERE id = ?", new DatabaseType[] { new DatabaseType(2,
						this.id) });

		if (attributes.length > 0 && attributes.length == id.length) {

			for (int i = 0; i < attributes.length; i++) {

				SaveObject.save(
						new Connection(null, Constants.DATABASE,
								Constants.USERNAME, Constants.PASSWORD),
						new String[] { "id", "attribute", "value" },
						"_attributes",
						new DatabaseType[] {
								new DatabaseType(2, id[i]),
								new DatabaseType(1, attributes[i].getValue()),
								new DatabaseType(1, attributes[i]
										.getAttribute()) });

			}

			return true;

		} else {

			return false;
		}

	}

	public void addId(int id) {
		int size = this.id.length;
		int[] ids = new int[size + 1];

		for (int i = 0; i < this.id.length; i++) {
			ids[i] = this.id[i];
		}
		ids[size] = id;
		this.id = ids;
	}

	public boolean add(Attribute attribute) {
		int size = attributes.length;
		size = size + 1;
		Attribute[] list = new Attribute[size];

		for (int i = 0; i < attributes.length; i++) {

			list[i] = attributes[i];

		}
		list[size - 1] = attribute;
		this.attributes = list;

		return true;
	}

	public void clear() {
		attributes = new Attribute[0];
	}

	public Attribute get(int index) {
		return attributes[index];
	}

	public boolean isEmpty() {

		if (attributes.length > 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean remove(int index) {

		if (attributes[index] != null) {
			attributes[index] = null;
			return true;
		}

		return false;
	}

	public int size() {
		return attributes.length;
	}

}
