package com.ydochung.kx.loader.product;

import java.util.*;

public class Tester {
	public static void main(String[] args) {
		if (args.length > 0){
			String inputFile = args[0];
			FileReaderI file = new FileReaderI(inputFile);
			System.out.println("Average is: " + file.getAverage());
		}
		else{
			System.out.println("Invalid command line");
			System.exit(1);			
		}
	}
}
