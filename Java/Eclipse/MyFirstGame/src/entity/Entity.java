package entity;

import graphics.Screen;

import java.util.Random;

import level.Level;

public class Entity {

	public double x, y;
	private boolean removed = false;
	
	public Level level;
	protected final Random random = new Random();

	public void update() {
	}

	public void render(Screen screen) {
	}

	public void remove() {
		// remove from level
		removed = true;
	}

	public boolean isRemoved() {
		return removed;
	}
	
}
