package graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private Spritesheet sheet;

	public static Sprite floor = new Sprite(16, 1, 0, Spritesheet.tiles);
	public static Sprite wall = new Sprite(16, 2, 0, Spritesheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0x000000);

	public static Sprite bullet_4 = new Sprite(16, 1, 1, Spritesheet.tiles);
	// player Sprites rygb

	public static Sprite player = new Sprite(16, 0, 0, Spritesheet.tiles);
	public static Sprite player_b = new Sprite(16, 0, 1, Spritesheet.tiles);
	public static Sprite player_g = new Sprite(16, 0, 2, Spritesheet.tiles);
	public static Sprite player_bg = new Sprite(16, 0, 3, Spritesheet.tiles);
	public static Sprite player_y = new Sprite(16, 0, 4, Spritesheet.tiles);
	public static Sprite player_yb = new Sprite(16, 0, 5, Spritesheet.tiles);
	public static Sprite player_yg = new Sprite(16, 0, 6, Spritesheet.tiles);
	public static Sprite player_ygb = new Sprite(16, 0, 7, Spritesheet.tiles);
	public static Sprite player_r = new Sprite(16, 0, 8, Spritesheet.tiles);
	public static Sprite player_rb = new Sprite(16, 0, 9, Spritesheet.tiles);
	public static Sprite player_rg = new Sprite(16, 0, 10, Spritesheet.tiles);
	public static Sprite player_rgb = new Sprite(16, 0, 11, Spritesheet.tiles);
	public static Sprite player_ry = new Sprite(16, 0, 12, Spritesheet.tiles);
	public static Sprite player_ryb = new Sprite(16, 0, 13, Spritesheet.tiles);
	public static Sprite player_ryg = new Sprite(16, 0, 14, Spritesheet.tiles);
	public static Sprite player_rygb = new Sprite(16, 0, 15, Spritesheet.tiles);

	public static Sprite[] player_sprites = { player, player_b, player_g, player_bg, player_y, player_yb, player_yg, player_ygb, player_r, player_rb, player_rg, player_rgb, player_ry, player_ryb, player_ryg, player_rygb };

	public Sprite(int size, int x, int y, Spritesheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * SIZE;
		this.y = y * SIZE;
		this.sheet = sheet;
		load();
	}

	public Sprite(int size, int colour) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColour(colour);
	}

	private void setColour(int colour) {
		for (int i = 0; i < SIZE * SIZE; i++) {
			pixels[i] = colour;
		}
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}

}