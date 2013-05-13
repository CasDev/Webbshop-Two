package org.casdev.web;

import org.casdev.web.model.Image;

public class Main {

	public static void main(String[] args) {

		Image img = new Image();
		img.setId(1);
		System.out.println(img.getAbsolutePath());

	}
}
