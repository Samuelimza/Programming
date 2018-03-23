import java.util.Random;

public class Vector {

	public double x, y, magnitude;

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
		calcMagnitude();
	}

	public Vector() {
		x = 1;
		y = 0;
		double randomAngle = 2 * Math.PI * (new Random()).nextDouble();
		rotate(randomAngle);
	}

	private void rotate(double angle) {
		double sin = Math.sin(angle);
		double cos = Math.cos(angle);
		double newX = x * cos - y * sin;
		double newY = x * sin + y * cos;
		this.x = newX;
		this.y = newY;
		calcMagnitude();
	}

	private void calcMagnitude() {
		this.magnitude = Math.sqrt(x * x + y * y);
	}

	public Vector add(Vector vector) {
		return new Vector(x + vector.x, y + vector.y);
	}

	public Vector subtract(Vector vector) {
		return new Vector(x - vector.x, y - vector.y);
	}

	public Vector multiply(double factor) {
		return new Vector(x * factor, y * factor);
	}

	public void setMagnitude(double newMagnitude) {
		if(magnitude ==0){
			System.out.println("ERROR");
			return;
		}
		double temp = newMagnitude / magnitude;
		x *= temp;
		y *= temp;
		calcMagnitude();
	}

}
