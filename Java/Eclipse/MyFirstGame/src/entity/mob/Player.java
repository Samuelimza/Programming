package entity.mob;

import entity.Projectile;
import level.Level;
import input.Keyboard;
import input.Mouse;
import graphics.Screen;
import graphics.Sprite;

public class Player extends Mob {

	private int timer = 0;
	private Keyboard input;
	Sprite[] sprites = Sprite.player_sprites;

	public Player(int x, int y, Keyboard input, Level level) {
		this.x = x;
		this.y = y;
		this.input = input;
		this.level = level;
		this.sprite = Sprite.player;
		collision_size = 6;
	}

	public void render(Screen screen) {
		screen.renderPlayerSprite((int)x - 8, (int)y - 8, sprite, 0x000000, 0x000000);
	}

	public void update() {
		timer++;
		int xa = 0, ya = 0;
		if (input.up) ya--;
		if (input.down) ya++;
		if (input.left) xa--;
		if (input.right) xa++;
		move(xa, ya);
		if (input.space) shoot();
		if (timer >= 60) {
			sprite = sprites[random.nextInt(16)];
			timer = 0;
		}
	}
	
}
