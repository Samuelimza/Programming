import java.util.Random;

public class Object {

	public Vector position, velocity, acceleration;
	public int r;

	public Object() {
		Random random = new Random();
		position = new Vector(random.nextDouble() * 300, random.nextDouble() * 168.75);
		velocity = new Vector();
		velocity.setMagnitude(5);
		acceleration = new Vector(0, 0);
	}

	public void update() {
		position = position.add(velocity);
		velocity = velocity.add(acceleration);
	}

	public void render(Screen screen) {
		System.out.println("HERE");
		screen.graphics.drawOval((int)(position.x - r), (int)(position.y - r), (int)(position.x + r), (int)(position.y + r));
	}

}
