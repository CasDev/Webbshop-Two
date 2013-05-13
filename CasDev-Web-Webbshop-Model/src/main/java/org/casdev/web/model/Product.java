package org.casdev.web.model;

import java.util.List;

import org.casdev.web.Constants;
import org.casdev.web.database.Connection;
import org.casdev.web.database.DatabaseType;
import org.casdev.web.database.GetObject;
import org.casdev.web.model.collection.AttributeCollection;

public class Product extends Object {

	private double price;
	private String name;
	private int id;
	private String desc;
	private AttributeCollection attribute;

	public Product() {
		this.price = 0;
		this.id = 0;
		this.name = "";
		this.desc = "";
		this.attribute = new AttributeCollection();
	}

	public AttributeCollection getAttribute() {
		return attribute;
	}

	public String getDesc() {
		return desc;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public boolean Save() {
		// TODO Auto-generated method stub
		return super.Save();
	}

	@Override
	public boolean Load() {

		if (this.id > 0) {

			List<java.lang.Object> list = GetObject.get(
					new Connection(null, Constants.DATABASE,
							Constants.USERNAME, Constants.PASSWORD),
					"_products", new DatabaseType[] {
							new DatabaseType(1, "name"),
							new DatabaseType(3, "price"),
							new DatabaseType(1, "description") },
					" WHERE id = ? ", new DatabaseType[] { new DatabaseType(2,
							this.id) }, null);

			if (list.size() > 0) {

				this.name = list.get(0).toString();
				this.price = Double.parseDouble(list.get(1).toString());
				this.desc = list.get(2).toString();

				list = GetObject
						.get(new Connection(null, Constants.DATABASE,
								Constants.USERNAME, Constants.PASSWORD),
								"_productattributes",
								new DatabaseType[] { new DatabaseType(1,
										"attributeid") },
								" WHERE productid = ? ",
								new DatabaseType[] { new DatabaseType(2,
										this.id) }, null);

				if (list.size() > 0) {

					for (int i = 0; i < list.size(); i++) {

						this.attribute.addId(Integer.parseInt(list.get(i)
								.toString()));

					}

				}

				return true;

			} else {

				return false;
			}

		} else {

			return false;
		}

	}

}
