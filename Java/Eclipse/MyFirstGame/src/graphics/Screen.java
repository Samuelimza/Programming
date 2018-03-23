package graphics;

import level.Level;

public class Screen {

	public int width;
	public int height;
	public int[] pixels;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}

	public void render(Level level) {
		level.render(this, Camera.X - width / 2, Camera.Y - height / 2);
	}

	public void renderSprite(int xp, int yp, Sprite sprite) {
		yp -= (Camera.Y - height / 2);
		xp -= (Camera.X - width / 2);
		if (xp + sprite.SIZE < 0 && yp + sprite.SIZE < 0) return;
		if (xp - sprite.SIZE > width && yp - sprite.SIZE > height) return;
		for (int y = yp; y < yp + sprite.SIZE; y++) {
			if (y < 0) continue;
			if (y >= height) break;
			for (int x = xp; x < xp + sprite.SIZE; x++) {
				if (x < 0) continue;
				if (x >= width) break;
				int index = (x - xp) + (y - yp) * sprite.SIZE;
				if (sprite.pixels[index] != 0xffff00ff) pixels[x + y * width] = sprite.pixels[index];
			}
		}
	}

	public void renderPlayerSprite(int xp, int yp, Sprite sprite, int stripe1, int stripe2) {
		yp -= (Camera.Y - height / 2);
		xp -= (Camera.X - width / 2);
		for (int y = yp; y < yp + sprite.SIZE; y++) {
			if (y < 0) continue;
			if (y >= height) break;
			for (int x = xp; x < xp + sprite.SIZE; x++) {
				if (x < 0) continue;
				if (x >= width) break;
				int col = sprite.pixels[(x - xp) + (y - yp) * sprite.SIZE];
				if (col != 0xffff00ff) {
					if (col == 0xff000000) pixels[x + y * width] = stripe1;
					else if (col == 0xffffffff) pixels[x + y * width] = stripe2;
					else
						pixels[x + y * width] = col;
				}
			}
		}
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
}
