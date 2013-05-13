package org.casdev.web.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.casdev.web.Constants;
import org.casdev.web.database.Connection;
import org.casdev.web.database.DatabaseType;
import org.casdev.web.database.GetObject;
import org.casdev.web.database.SaveObject;

public class News extends Object {

	private Integer id;
	private String title;
	private Calendar date;
	private String content;

	public News() {
		this.id = 0;
		this.title = "";
		this.date = Calendar.getInstance();
		this.content = "";
	}

	public String getContent() {
		return content;
	}

	public Integer getId() {
		return id;
	}

	public Calendar getDate() {
		return date;
	}

	public String getTitle() {
		return title;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDate(int year, int month, int day, int hour, int minute,
			int second) {
		this.date.set(Calendar.YEAR, year);
		this.date.set(Calendar.MONTH, month);
		this.date.set(Calendar.DATE, day);

		this.date.set(Calendar.HOUR, hour);
		this.date.set(Calendar.MINUTE, minute);
		this.date.set(Calendar.SECOND, second);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public boolean Load() {
		if (id <= 0) {
			return false;
		} else {

			List<java.lang.Object> list = GetObject.get(
					new Connection(null, Constants.DATABASE,
							Constants.USERNAME, Constants.PASSWORD), "_news",
					new DatabaseType[] { new DatabaseType(1, "date"),
							new DatabaseType(1, "title"),
							new DatabaseType(1, "content") }, " WHERE id = ? ",
					new DatabaseType[] { new DatabaseType(2, this.id) }, null);

			if (list.size() > 0) {

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

				try {
					this.date.setTime(format.parse(list.get(0).toString()));

					this.title = list.get(1).toString();
					this.content = list.get(2).toString();
				} catch (Exception e) {

				}

				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public boolean Save() {

		if (this.id == 0) {

			int id = SaveObject.save(new Connection(null, Constants.DATABASE,
					Constants.USERNAME, Constants.PASSWORD), new String[] {
					"title", "date", "content" }, "_news", new DatabaseType[] {
					new DatabaseType(1, this.title),
					new DatabaseType(4, this.date),
					new DatabaseType(1, this.content) });

			if (id > 0) {
				this.id = id;

				return true;
			} else {
				return false;
			}
		} else {

			// TODO: update?
			
			return false;
		}

	}

}
