import java.awt.Graphics;


public class Screen {
	public int width;
	public int height;
	public int[] pixels;
	public Game game;

	public Screen(int width, int height, Game game) {
		this.width = width;
		this.height = height;
		this.game = game;
		pixels = new int[width * height];
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render(Graphics g) {
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(game.tiles[i + j * 3] == 1){
					drawX(i * width * 4 / 3, j * height * 4 / 3, g);
				}else if(game.tiles[i + j * 3] == 2){
					drawO(i * width * 4 / 3, j * height * 4 / 3, g);
				}
			}
		}
	}
	
	public void drawX(int x, int y, Graphics g){
		g.drawLine(x, y, x + width * 4 / 3, y + height * 4 / 3);
		g.drawLine(x, y + height * 4 / 3, x + width * 4 / 3, y);
	}
	
	public void drawO(int x, int y, Graphics g){
		g.drawArc(x , y, width * 4 / 3, height * 4 / 3, 0, 360);
	}
}
