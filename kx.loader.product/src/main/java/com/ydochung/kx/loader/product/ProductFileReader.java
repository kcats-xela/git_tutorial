package com.ydochung.kx.loader.product;

import java.io.BufferedReader;
import java.io.*;

public class ProductFileReader {

	public static void main(String[] args) {
		String inputFile = "inputFile/products.txt";
		try{			
			BufferedReader br = 
					new BufferedReader(new FileReader(inputFile));
			
			while (true){
				String line = br.readLine();
				if (line == null) break;
				System.out.println(line);
			}
			br.close();
		}
		catch (IOException ex){
			System.out.println("Can't open that file");
			
		}

			
	}

}
