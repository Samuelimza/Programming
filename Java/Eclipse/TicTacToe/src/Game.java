import Input.Mouse;


public class Game {
	public byte[] tiles = new byte[9];
	public int scoreA = 0, scoreB = 0, i, j;
	public int[] first = {4, 4, 1, 4, 7, 3, 4, 5};
	public int[] second = {4, 2, 1, 1, 1, 3, 3, 3};
	
	public Game(){
		for(byte i = 0; i < 10; i++){
			//tiles[i] = 0;
		}
		tiles[0] = 1; tiles[1] = 1; tiles[2] = 2;
		tiles[3] = 2; tiles[4] = 1; tiles[5] = 1;
		tiles[6] = 1; tiles[7] = 2; tiles[8] = 2;
	}
	
	public void setValue(byte pos, byte val){
		tiles[pos] = val;
	}
	
	public boolean checkWin(){
		for(int k = 0; k < 9; k++){
			i = first[k];
			j = second[k];
			if(tiles[i] == tiles[i + j] && tiles[i] == tiles[i - j]) return true;
		}
		return false;
	}
	
	public void update(){
		if(Mouse.getButton() == 1){
			tiles[0] = 1;
		}else if(Mouse.getButton() == 3){
			tiles[0] = 2;
		}
	}
}
