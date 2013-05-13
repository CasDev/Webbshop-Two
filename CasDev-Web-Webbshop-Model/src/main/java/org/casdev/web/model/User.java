package org.casdev.web.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.casdev.web.Constants;
import org.casdev.web.database.Connection;
import org.casdev.web.database.DatabaseType;
import org.casdev.web.database.GetObject;
import org.casdev.web.database.SaveObject;
import org.casdev.web.database.UpdateObject;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class User extends Object {

	private Calendar registered;
	private String username;
	private String password;
	private int id;
	private boolean online;

	public User() {
		this.registered = Calendar.getInstance();
		this.username = "";
		this.password = "";
		this.id = 0;
		this.online = false;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPassword(String password) {
		BASE64Encoder encoder = new BASE64Encoder();
		password = encoder.encode(password.getBytes());

		this.password = password;
	}

	public String getPassword() {
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			password = new String(decoder.decodeBuffer(password));
		} catch (Exception e) {

		}
		return password;
	}

	public boolean isOnline() {
		return online;
	}

	public void setRegistered(int year, int month, int day) {
		this.registered.add(Calendar.YEAR, year);
		this.registered.add(Calendar.MONTH, month);
		this.registered.add(Calendar.DATE, day);
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public Calendar getRegistered() {
		return registered;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public boolean Load() {

		if (this.username.length() > 0 && this.password.length() > 0) {

			List<java.lang.Object> list = GetObject.get(
					new Connection(null, Constants.DATABASE,
							Constants.USERNAME, Constants.PASSWORD), "_users",
					new DatabaseType[] { new DatabaseType(DatabaseType.INTEGER, "id"),
							new DatabaseType(DatabaseType.DATE, "registered") },
					" WHERE username = LOWER(?) AND password = ? ",
					new DatabaseType[] { new DatabaseType(DatabaseType.STRING, this.username),
							new DatabaseType(DatabaseType.STRING, this.password) }, null);

			if (list.size() > 0) {

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

				try {
					this.id = Integer.parseInt(list.get(0).toString());

					this.registered.setTime(format
							.parse(list.get(1).toString()));

					this.online = true;
				} catch (Exception e) {

					return false;
				}

				return true;

			}

		}
		return false;
	}

	@Override
	public boolean Save() {

		if (this.id > 0) {

			boolean isTrue;

			isTrue = UpdateObject.update(
					new Connection(null, Constants.DATABASE,
							Constants.USERNAME, Constants.PASSWORD),
					new String[] { "username", "password" },
					new DatabaseType[] { new DatabaseType(1, this.username),
							new DatabaseType(1, this.password) }, "_users",
					" WHERE id = ? ", new DatabaseType[] { new DatabaseType(2,
							this.id) });

			return isTrue;
		} else {

			int id = SaveObject.save(new Connection(null, Constants.DATABASE,
					Constants.USERNAME, Constants.PASSWORD), new String[] {
					"username", "password", "registered" }, "_users",
					new DatabaseType[] { new DatabaseType(1, this.username),
							new DatabaseType(1, this.password),
							new DatabaseType(1, this.registered) });

			if (id > 0) {
				this.id = id;

				return true;
			}

			return false;
		}

	}

}
