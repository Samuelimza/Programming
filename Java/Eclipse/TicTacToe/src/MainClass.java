import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import Input.Mouse;


public class MainClass extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	public static int width = 100;
	public static int height = 100;
	public static int scale = 4;
	public static String title = "TicTacToe";
	
	private Thread thread;
	private JFrame frame;

	private boolean running = false;

	private Screen screen;
	private Game game;
	private Mouse mouse;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	
	public MainClass() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		
		frame = new JFrame();
		game = new Game();
		screen = new Screen(width, height, game);
		mouse = new Mouse();
		
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
		//screen.update();
		game.update();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		screen.clear();
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.WHITE);
		screen.render(g);
		g.setFont(new Font("Verdana", 0, 50));
		g.drawLine(width * 4 / 3, 0, width * 4 / 3, height * 4);
		g.drawLine(width * 8 / 3, 0, width * 8 / 3, height * 4);
		g.drawLine(0, height * 4 / 3, width * 4, height * 4 / 3);
		g.drawLine(0, height * 8 / 3, width * 4, height * 8 / 3);
		//g.drawLine((width * scale) / 2, 0, (width * scale) / 2, height * scale);
		//g.drawLine(0, (height * scale) / 2, width * scale, (height * scale) / 2);
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args){
		MainClass object = new MainClass();
		object.frame.setResizable(false);
		object.frame.setTitle(MainClass.title);
		object.frame.add(object);
		object.frame.pack();
		object.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		object.frame.setLocationRelativeTo(null);
		object.frame.setVisible(true);

		object.start();
	}
	
}
