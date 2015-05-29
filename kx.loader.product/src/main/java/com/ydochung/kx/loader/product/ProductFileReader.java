package com.ydochung.kx.loader.product;

import java.io.*;
import java.util.ArrayList;

public class ProductFileReader {
	public static void main(String[] args) {
		// new feature: now read filename from args
		String inputFile = "inputFile/products.txt";
		// comment: always declare your variable with interface (where possible) such as List, Set, etc
		ArrayList <Double> grades = new ArrayList <Double>();
		readFileAndGetAverage(inputFile, grades);
		
	}

	// comment: low cohesion - how can you refactor for high cohesion?
	// question: when you receive grades as an argument, will it have all the grades at the end?
	//		in other words, does it change after this method? This is called (pass-by-value, pass-by-reference).
	public static void readFileAndGetAverage(String filename, ArrayList<Double> grades){
		String inputFile = filename;
		double sum = 0;
		try{			
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			// new feature: now the file is 'pipe-delimited' format (i.e. alex chung|102) How can you loop without using 'infinite-loop'?
			while (true){
				String name  = br.readLine();
				String grade = br.readLine();
				if (name == null) break;
				// comment: parseDouble has possible exception. NumberFormatException, handle that exception
				double d_grade = Double.parseDouble(grade);
				grades.add(d_grade);
			}
			int numStudents = grades.size();
			
			// comment: sum as you read file
			for (int i = 0; i < numStudents; i++){
				sum += grades.get(i);
			}
			System.out.println("Average of all students: " + sum/numStudents);
			// comment: put this in the 'finally' clause since br will never get closed if IOException occurs before this line.
			br.close();
		}
		catch (IOException ex){
			System.out.println("Can't open that file");
		}	
	}
}