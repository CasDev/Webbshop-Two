package org.casdev.web.model.collection;

import java.util.ArrayList;
import java.util.List;

import org.casdev.web.model.Object;
import org.casdev.web.model.Page;

public class PageCollection extends Object {

	private List<Page> pages;

	public PageCollection() {
		this.pages = new ArrayList<Page>();
	}

	@Override
	public boolean Load() {

		for (Page page : pages) {

			boolean isTrue;

			isTrue = page.Load();

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

	public boolean add(Page page) {
		return pages.add(page);
	}

	public void clear() {
		pages.clear();
	}

	public Page get(int index) {
		return pages.get(index);
	}

	public boolean isEmpty() {

		if (pages.size() > 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean remove(int index) {

		if (pages.get(index) != null) {

			pages.remove(index);
			return true;

		}

		return false;
	}

	public int size() {
		return pages.size();
	}

}
