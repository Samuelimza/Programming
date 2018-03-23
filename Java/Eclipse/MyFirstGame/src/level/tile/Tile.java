package level.tile;

import graphics.Screen;
import graphics.Sprite;

public class Tile {

	public boolean solid;
	public Sprite sprite;

	public static Tile floor = new Tile(Sprite.floor, false);
	public static Tile wall = new Tile(Sprite.wall, true);
	public static Tile voidTile = new Tile(Sprite.voidSprite, true);

	public Tile(Sprite sprite, boolean solid) {
		this.sprite = sprite;
		this.solid = solid;
	}

	public void render(Screen screen, int xp, int yp) {
		for (int y = 0; y < sprite.SIZE; y++) {
			int ya = yp + y;
			if (ya < 0) continue;
			if (ya >= screen.height) break;
			for (int x = 0; x < sprite.SIZE; x++) {
				int xa = xp + x;
				if (xa < 0) continue;
				if (xa >= screen.width) break;
				screen.pixels[xa + ya * screen.width] = sprite.pixels[x + y * sprite.SIZE];
			}
		}
	}

	public boolean isSolid() {
		return solid;
	}

}
