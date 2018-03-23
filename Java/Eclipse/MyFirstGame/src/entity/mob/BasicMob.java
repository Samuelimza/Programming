package entity.mob;

import level.Level;
import graphics.Screen;
import graphics.Sprite;

public class BasicMob extends Mob {

	private int temporary = 0;
	protected int stripe1 = random.nextInt(0xffffff);
	protected int stripe2 = random.nextInt(0xffffff);	

	public BasicMob(Sprite sprite, int x, int y, Level level) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.level = level;
		this.level.entities.add(this);
		collision_size = 6;
	}

	public void update() {
		temporary++;
		if (temporary % 60 == 0) System.out.println(temporary / 60);
		if (temporary % 60 == 0) {
			dir = random.nextInt(4);
		}
		if (dir == 0) move(0, -1);
		else if (dir == 1) move(1, 0);
		else if (dir == 2) move(0, 1);
		else if (dir == 3) move(-1, 0);
		shoot();
	}

	public void render(Screen screen) {
		screen.renderPlayerSprite((int) x - 8, (int) y - 8, sprite, stripe1, stripe2);
	}

}
