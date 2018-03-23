package entity.mob;

import input.Mouse;
import entity.Entity;
import entity.Projectile;
import graphics.Sprite;

public class Mob extends Entity {

	protected Sprite sprite;
	protected int collision_size;
	protected int dir = 0;
	protected boolean moving = false;

	public void move(int xa, int ya) {
		
		if (!collision(xa, 0)) {
			x += xa;
		}
		if (!collision(0, ya)) {
			y += ya;
		}

	}

	//public void update() {
	//}

	protected boolean collision(int x, int y) {
		if (level.getTile(((int)this.x + (collision_size * x)) >> 4, ((int)this.y + (collision_size * y)) >> 4).isSolid()) return true;
		return false;
	}
	
	public void shoot() {
		Projectile p = new Projectile(this, (int)x, (int)y, (Mouse.getX() / 3 - 150), (Mouse.getY() / 3 - 81), Sprite.bullet_4);
		p.level = level;
		level.entities.add(p);
	}


	public void render() {
	}

}
