package org.casdev.web.database;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		}
	}

	private java.sql.Connection conn;

	public Connection(String server, String database, String username,
			String password) {

		if (server == null || server.equals("localhost")) {
			server = "127.0.0.1";
		}

		try {
			this.conn = DriverManager.getConnection("jdbc:mysql://" + server + "/"
					+ database, username, password);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
		}

	}

	public java.sql.Connection getConnection() {
		return this.conn;
	}

	public void close() {

		try {
			if (this.conn != null && !this.conn.isClosed()) {
				this.conn.close();
			}
		} catch (SQLException e) {
		}
	}

}
