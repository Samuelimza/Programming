import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


public class Screen {
	public InputStream pic;
	public int flag;
	public int width;
	public int height;
	public int[] pixels;
	public int[] canv; // Y = 0.299R + 0.587G + 0.114B
	public float[][] weight0;
	public float[][] weight1;
	public float[][] weight2;

	public Screen(int width, int height) {
		this.flag = 0;
		this.width = width;
		this.height = height;
		canv = new int[38 * 38];
		pixels = new int[width * height];
		weight0 = new float[38 * 38][16];
		weight1 = new float[16][16];
		weight2 = new float[16][10];
		
		try{
			File pic = new File("C:/Osama/Eclipse/Classify.png");
			if(pic.exists()){
				BufferedImage image = ImageIO.read(pic);
				if(image.getHeight() == 38 && image.getWidth() == 38){
					image.getRGB(0, 0, image.getWidth(), image.getHeight(), canv, 0, image.getWidth());
					System.out.println(canv[0]);
				}
			}
			InputStream in = getClass().getResourceAsStream("/w0.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String s = "";
			int counter = 0;
			while((s = br.readLine()) != null){
				extractData(s, 0, counter);
				counter++;
			}
			in = getClass().getResourceAsStream("/w1.txt");
			br = new BufferedReader(new InputStreamReader(in));
			s = "";
			counter = 0;
			while((s = br.readLine()) != null){
				extractData(s, 1, counter);
				counter++;
			}
			in = getClass().getResourceAsStream("/w2.txt");
			br = new BufferedReader(new InputStreamReader(in));
			s = "";
			counter = 0;
			while((s = br.readLine()) != null){
				extractData(s, 2, counter);
				counter++;
			}
			br.close();
		}catch(FileNotFoundException ex){
			System.out.println("File move");
		} catch (IOException ex) {
			System.out.println("Reading problem");
		}
	}
	
	public void extractData(String s, int inpNo, int index){
		String w = "";
		int count = 0;
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if(c != ','){
				w += c;
			}else{
				if(inpNo == 0){
					weight0[index][count] = Float.parseFloat(w);
				}else if(inpNo == 1){
					weight1[index][count] = Float.parseFloat(w);
				}else if(inpNo == 2){
					weight2[index][count] = Float.parseFloat(w);
				}
				count++;
				w = "";
			}
		}
		if(inpNo == 0){
			weight0[index][count] = Float.parseFloat(w);
		}else if(inpNo == 1){
			weight1[index][count] = Float.parseFloat(w);
		}else if(inpNo == 2){
			weight2[index][count] = Float.parseFloat(w);
		}
	}

	public void render() {
		if(flag == 21){
			File file = new File("C:/Osama/Eclipse/Spritesheet.png");//.getAbsoluteFile();    
			System.out.println(file.exists());
		    BufferedImage image = null;
		    try{
		    	image = ImageIO.read(file);     
		            //ImageIO.write(image, "jpg", new File("C:/Users/Ashhad/Desktop/output.jpg"));
		            //ImageIO.write(image, "png", new File("C:/Users/Ashhad/Desktop/output.png"));
		            //ImageIO.write(image, "gif", new File("C:/Users/Ashhad/Desktop/output.gif"));
		            //ImageIO.write(image, "bmp", new File("C:/Users/Ashhad/Desktop/output.bmp"));
		    } 
		    catch (IOException e){
		    	e.printStackTrace();
		    }
		    System.out.println("donehurray");
		    flag = 1;
		}
	}
	
	public void update() {
		
	}
	
	public float[][] mult(float[][] m1, float[][] m2){
		float[][] result = new float[m1.length][m2[0].length];
		for(int i = 0; i < m1.length; i++){
			for(int j = 0; j < m2[0].length; j++){
				for(int k = 0; k < m1[0].length; k++){
					result[i][j] += m1[i][k] * m2[k][j];
				}
			}
		}
		return result;
	}

	public float[][] lin(float[][] x){
		float[][] result = new float[x.length][x[0].length];
		for(int i = 0; i < x.length; i++){
			for(int j = 0; j < x[i].length; j++){
				result[i][j] = 1 / (1 + (float)Math.exp(-1 * x[i][j]));
			}
		}
		return result;
	}
	
	public int predict(){
		//System.out.println(Math.E);
		//System.out.println(Math.exp(Math.E));
		// canv 784 // w0 784 16 w1 16 16 w2 16 10
		float[][] l1 = lin(mult(canv, weight0));
		float[][] l2 = lin(mult(l1, weight1));
		float[][] l3 = lin(mult(l2, weight2));
		float max = 0;
		int index = -1;
		for(int i = 0; i < 10; i++){
			if(l3[0][i] > max){
				max = l3[0][i];
				index = i;
			}
		}
		return index;
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
}
