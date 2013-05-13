package org.casdev.web.model.collection;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.casdev.web.Constants;
import org.casdev.web.database.Connection;
import org.casdev.web.database.DatabaseType;
import org.casdev.web.database.GetObject;
import org.casdev.web.model.News;
import org.casdev.web.model.Object;
import org.casdev.web.model.Product;

public class NewsCollection extends Object {

	private List<News> _news;

	public NewsCollection() {
		this._news = new ArrayList<News>();
	}

	@Override
	public boolean Load() {

		for (News news : _news) {
			
			boolean isTrue;
			
			isTrue = news.Load();
			
			if (!isTrue) {
				return false;
			}

		}
		
		return true;

	}
	
	@Override
	public boolean Save() {
		return false;
	}
	
	public boolean getRandom(int numbers) {

		if (numbers > 0) {

			List<java.lang.Object> list = GetObject.get(
					new Connection(null, Constants.DATABASE,
							Constants.USERNAME, Constants.PASSWORD),
					"_products", new DatabaseType[] {
							new DatabaseType(2, "id"),
							new DatabaseType(1, "title"),
							new DatabaseType(3, "date") },
					" ORDER BY RAND() LIMIT ? ",
					new DatabaseType[] { new DatabaseType(2, numbers) }, null);

			this.clear();
			if (list.size() > 0) {

				for (int i = 0; i < list.size(); i = i + 3) {

					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					News news = new News();

					try {
						news.setId(Integer.parseInt(list.get(i).toString()));
						int n = i + 1;
						news.getDate().setTime(format.parse(list.get(n).toString()));
						n = n + 1;
						news.setTitle(list.get(n).toString());
						
						this.add(news);
					} catch (Exception e) {

					}

				}

			}

			return true;
		} else {
			return false;
		}
	}

	public boolean add(News news) {
		return _news.add(news);
	}

	public void clear() {
		_news.clear();
	}

	public News get(int index) {
		return _news.get(index);
	}

	public boolean isEmpty() {

		if (_news.size() > 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean remove(int index) {

		if (_news.get(index) != null) {
			_news.remove(index);
			return true;
		}

		return false;
	}

	public int size() {
		return _news.size();
	}

}
