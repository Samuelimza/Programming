package graphics;

import entity.mob.Player;

public class Camera {

	private Player player;

	public static int X = 0, Y = 0;
	public static int xRange, yRange;

	public Camera(Player player) {
		this.player = player;
	}

	public void update() {
		X = (int)player.x;
		Y = (int)player.y;
	}

}
