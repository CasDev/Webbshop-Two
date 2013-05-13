package org.casdev.web.database;

import java.util.List;

public class Main {

	public static void main(String[] args) {

		List<java.lang.Object> list = GetObject.get(new Connection(null,
				"test", "casdev-user", "casdev-user-password"), "_picture",
				new DatabaseType[] {
						new DatabaseType(DatabaseType.STRING, "path"),
						new DatabaseType(DatabaseType.STRING, "extention"),
						new DatabaseType(DatabaseType.INTEGER, "productid") },
				" WHERE id = ? ", new DatabaseType[] { new DatabaseType(
						DatabaseType.INTEGER, 1) }, null);
		System.out.println("SIZE == " + list.size());
		int i = 0;
		
		for (Object object : list) {
			System.out.println(i + "   ===   " + object);
			i++;
		}
	}
}
