package level;

import entity.Entity;
import graphics.Screen;
import graphics.Spritesheet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import level.tile.Tile;

public class Level {

	private int temp = 0;

	public Tile[] tiles;
	public int width, height;
	private Random random;

	public List<Entity> entities = new ArrayList<Entity>();

	public Level(String path) {
		loadLevel(path);
	}

	public Level(int seed, int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new Tile[width * height];
		random = new Random();
		generateLevel(seed);
	}

	private void generateLevel(int seed) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int boom = random.nextInt(2);
				if (boom == 0) tiles[x + y * width] = Tile.floor;
				else if (boom == 1) tiles[x + y * width] = Tile.wall;
			}
		}
	}

	// 404040 = wall
	// 808080 = floor
	private void loadLevel(String path) {
		int[] pixels;
		try {
			BufferedImage image = ImageIO.read(Spritesheet.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new Tile[width * height];
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
			for (int i = 0; i < pixels.length; i++) {
				if (pixels[i] == 0xff808080) tiles[i] = Tile.floor;
				else if (pixels[i] == 0xff404040) tiles[i] = Tile.wall;
				else
					tiles[i] = Tile.voidTile;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// convert int to tiles
	}

	public void render(Screen screen, int xb0, int yb0) {
		int x0 = xb0 >> 4;
		int y0 = yb0 >> 4;
		int x1 = (xb0 + screen.width) >> 4;
		int y1 = (yb0 + screen.height) >> 4;
		for (int y = y0; y <= y1; y++) {
			for (int x = x0; x <= x1; x++) {
				getTile(x, y).render(screen, (x << 4) - xb0, (y << 4) - yb0);
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
	}

	public void update() {
		temp++;
		if (temp % 60 == 0) System.out.println(temp / 60);
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isRemoved()) {
				entities.remove(i);
				continue;
			}
			entities.get(i).update();
		}
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		return tiles[x + y * width];
	}

}
