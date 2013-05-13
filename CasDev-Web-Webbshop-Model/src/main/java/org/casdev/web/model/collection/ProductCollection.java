package org.casdev.web.model.collection;

import java.util.ArrayList;
import java.util.List;

import org.casdev.web.Constants;
import org.casdev.web.database.Connection;
import org.casdev.web.database.DatabaseType;
import org.casdev.web.database.GetObject;
import org.casdev.web.model.Object;
import org.casdev.web.model.Product;

public class ProductCollection extends Object {

	private List<Product> products;

	public ProductCollection() {
		this.products = new ArrayList<Product>();
	}

	public List<Product> getProducts() {
		return products;
	}

	public boolean getRandom(int numbers) {

		if (numbers > 0) {

			List<java.lang.Object> list = GetObject.get(
					new Connection(null, Constants.DATABASE,
							Constants.USERNAME, Constants.PASSWORD),
					"_products",
					new DatabaseType[] { new DatabaseType(2, "id"), },
					" ORDER BY RAND() LIMIT ? ",
					new DatabaseType[] { new DatabaseType(2, numbers) }, null);

			this.clear();
			if (list.size() > 0) {

				for (int i = 0; i < list.size(); i++) {

					Product product = new Product();
					product.setId(Integer.parseInt(list.get(i).toString()));

					this.add(product);
				}

			}

			for (Product p : this.products) {

				p.Load();

			}

			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean Load() {

		if (products.size() > 1) {

			List<java.lang.Object> list = GetObject.get(
					new Connection(null, Constants.DATABASE,
							Constants.USERNAME, Constants.PASSWORD),
					"_products",
					new DatabaseType[] { new DatabaseType(2, "id") }, null,
					null, null);

			if (list.size() > 0) {

				for (int i = 0; i < list.size(); i++) {

					Product p = new Product();
					p.setId(Integer.parseInt(list.get(i).toString()));
					this.add(p);

				}
				
				for (Product p : this.products) {

					boolean loaded = p.Load();
					if (!loaded) {
						
						return false;
					}

				}
				
				return true;

			} else {
				
				return false;
			}

		} else {

			for (Product product : products) {

				boolean isTrue;

				isTrue = product.Load();

				if (!isTrue) {
					return false;
				}

			}

		}

		return false;
	}

	@Override
	public boolean Save() {
		return false;
	}

	public boolean add(Product product) {
		return products.add(product);
	}

	public void clear() {
		products.clear();
	}

	public Product get(int index) {
		return products.get(index);
	}

	public boolean isEmpty() {

		if (products.size() > 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean remove(int index) {

		if (products.get(index) != null) {

			products.remove(index);
			return true;

		}

		return false;
	}

	public int size() {
		return products.size();
	}

}
