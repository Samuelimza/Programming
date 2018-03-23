import entity.mob.BasicMob;
import entity.mob.Mob1;
import entity.mob.Player;
import graphics.Camera;
import graphics.Screen;
import graphics.Sprite;
import input.Keyboard;
import input.Mouse;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

import level.Level;

public class MainGame extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3;
	public static String title = "Game";

	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Mouse mouse;
	private Level level;
	private Camera camera;
	private Player player;

	private boolean running = false;

	private Screen screen;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public MainGame() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);

		screen = new Screen(width, height);
		level = new Level("/textures/levels/level1.png");

		frame = new JFrame();
		key = new Keyboard();
          		player = new Player(50, 85, key, level);
		camera = new Camera(player);
		mouse = new Mouse();

		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			boolean found = false;
			int tx = random.nextInt(level.width);
			int ty = random.nextInt(level.height);
			while (!found) {
				if (!level.getTile(tx, ty).isSolid()) {
					found = true;
					break;
				}
				tx = random.nextInt(level.width);
				ty = random.nextInt(level.height);
			}
			Mob1 b = new Mob1(Sprite.player, tx * 16, ty * 16, level);
			level.entities.add(b);
		}

		frame.addKeyListener(key);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		setFocusable(false);
		frame.requestFocus();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + " | " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	public void update() {
		key.update();
		camera.update();
		player.update();
		level.update();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		screen.render(level);
		player.render(screen);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", 0, 50));
		g.drawLine((width * scale) / 2, 0, (width * scale) / 2, height * scale);
		g.drawLine(0, (height * scale) / 2, width * scale, (height * scale) / 2);
		g.drawString("Player :" + player.x + ", " + player.y, 0, 40);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		MainGame game = new MainGame();
		game.frame.setResizable(false);
		game.frame.setTitle(MainGame.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();
	}

}