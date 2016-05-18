package util;

import java.util.ArrayList;

public class Resources {

	private ArrayList<Image> images;

	public void loadResources() {
		images = new ArrayList<Image>();
		for (int i = 0; i < 14; i++) {
			images.add(new Image("/2048.png", 0, (i * 128), 128, 128));
		}
	}

	public Image getImage(int imageValue) {
		return (imageValue >= 0 && imageValue < 15) ? images.get(imageValue) : images.get(0);
	}
}
