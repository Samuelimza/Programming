import java.util.Scanner;
public class myClass {
	public int height, width, n;
	Scanner reader = new Scanner(System.in);
	
	public void inputAB(){
		System.out.println("Enter the value of height: ");
		height = reader.nextInt();
		System.out.println("Enter the value of width: ");
		width = reader.nextInt();
	}
	public void inputN(){
		System.out.println("Enter the value of N: ");
		n = reader.nextInt();
	}
	public float area(){
		return height * width;
	}
	public int sumOdigits(){
		int sum = 0;
		while(n > 0){
			sum += n % 10;
			n /= 10;
		}
		return sum;
	}
	public static void main(String args[]){
		myClass obj = new myClass();
		obj.inputAB();
		System.out.println("Area of rectangle is " + obj.area());
		obj.inputN();
		System.out.println("Sum of Digits is " + obj.sumOdigits());
	}
}
