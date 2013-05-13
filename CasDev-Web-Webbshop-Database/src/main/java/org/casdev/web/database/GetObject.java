package org.casdev.web.database;

import java.sql.Blob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GetObject {

	/**
	 * @param connection
	 *            The connection object
	 * @param table
	 *            The database table
	 * @param parameters
	 *            The parameters to get from the database
	 * @param where
	 *            An WHERE-clause - null:able
	 * @param whereParams
	 *            Values to assign to the WHERE-clause
	 * @param order
	 *            An ORDER-clause - null:able
	 * @return An List<Object> full of the variables that you wanted
	 *         (parameters)
	 */
	public static List<Object> get(Connection connection, String table,
			DatabaseType[] parameters, String where,
			DatabaseType[] whereParams, String order) {

		java.sql.Connection conn = connection.getConnection();
		String select = buildSelectQuery(parameters, table, where, order);
		List<Object> objects = new ArrayList<Object>();

		try {
			PreparedStatement statement = conn.prepareStatement(select);

			if (where != null && whereParams != null) {

				for (int i = 1; i <= whereParams.length; i++) {

					int n = (i - 1);
					DatabaseType value = whereParams[n];

					switch (value.getType()) {
					case 1:
						statement.setString(i, value.getValue().toString());
						break;
					case 2:
						statement.setInt(i,
								Integer.parseInt(value.getValue().toString()));
						break;
					case 3:
						statement
								.setDouble(i, Double.parseDouble(value
										.getValue().toString()));
						break;
					case 4:
						Calendar cal = (Calendar) value.getValue();

						statement.setDate(i, new Date(cal.getTime().getTime()));
						break;
					case 5:
						statement.setBoolean(i,
								Boolean.valueOf(value.getValue().toString()));
						break;
					case 6:
						statement.setByte(i,
								Byte.parseByte(value.getValue().toString()));
						break;
					default:
						break;
					}

				}

			}

			ResultSet set = statement.executeQuery();

			while (set.next()) {

				for (int i = 1; i <= parameters.length; i++) {

					DatabaseType value = parameters[(i - 1)];

					switch (value.getType()) {
					case 1:
						objects.add(set.getString(i));
						break;
					case 2:
						objects.add(set.getInt(i));
						break;
					case 3:
						objects.add(set.getDouble(i));
						break;
					case 4:
						objects.add(set.getDate(i));
						break;
					case 5:
						objects.add(set.getBoolean(i));
						break;
					case 6:
						objects.add(set.getByte(i));
						break;
					case 7:
						Blob blob = set.getBlob(i);

						int length = (int) blob.length();
						byte[] array = blob.getBytes(1, length);

						blob.free();
						objects.add(array);
						break;
					default:
						break;
					}

				}

			}

		} catch (SQLException e) {
		} finally {
			connection.close();
		}

		return objects;

	}

	private static String buildSelectQuery(DatabaseType[] parameters,
			String table, String where, String order) {

		StringBuilder builder = new StringBuilder();

		builder.append("SELECT ");

		int last = parameters.length;

		for (int i = 1; i <= last; i++) {

			builder.append(parameters[(i - 1)].getValue().toString());

			if (i != last) {

				builder.append(", ");

			}

		}

		builder.append(" FROM ");
		builder.append(table);

		if (where != null && where.length() > 0) {

			builder.append(where);

		}

		if (order != null && order.length() > 0) {

			builder.append(order);

		}

		return builder.toString();
	}

}
