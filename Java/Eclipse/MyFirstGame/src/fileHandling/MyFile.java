package fileHandling;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.*;

public class MyFile {

	private Scanner in;
	private File file;
	BufferedWriter out; 


	public MyFile( String path) {
		try {
			URL url = MyFile.class.getResource(path);
			file = new File(url.toURI());
			in = new Scanner(file);
		} catch (Exception e) {
			System.out.println("ERROR. Could not deal with File");
		}
	}

	public String fileContent() {
		String string = " ";
		while (in.hasNext()) {
			string += in.next() + " ";
		}
		return string;
	}
	
	public void write(boolean newLine, String data){
		try {
			out  = new BufferedWriter(new FileWriter(file));
			out.write(data);
		} catch (IOException e) {
			System.out.println("ERROR. Could not Write");
		}
	}

	public void closeFile() {
		in.close();
		try {
			out.close();
		} catch (IOException e) {
		}
	}

}