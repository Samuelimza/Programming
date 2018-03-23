import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Screen {

	public Graphics graphics;

	List<Object> circles = new ArrayList<Object>();

	public int width;
	public int height;
	public int[] pixels;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		for (int i = 0; i < 10; i++) {
			circles.add(new Object());
		}
	}

	public void render() {
		for (Object object : circles) {
			object.render(this);
		}
	}

	public void update() {
		for (Object object : circles) {
			object.update();
		}
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

}
