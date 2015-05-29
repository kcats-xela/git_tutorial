package com.ydochung.kx.loader.product;

import java.io.*;
import java.util.ArrayList;

public class ProductFileReader {
	public static void main(String[] args) {
		String inputFile = "inputFile/products.txt";
		ArrayList <Double> grades = new ArrayList <Double>();
		readFileAndGetAverage(inputFile, grades);
		
	}

	public static void readFileAndGetAverage(String filename, ArrayList<Double> grades){
		String inputFile = filename;
		double sum = 0;
		try{			
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			while (true){
				String name  = br.readLine();
				String grade = br.readLine();
				if (name == null) break;
				double d_grade = Double.parseDouble(grade);
				grades.add(d_grade);
			}
			int numStudents = grades.size();
			for (int i = 0; i < numStudents; i++){
				sum += grades.get(i);
			}
			System.out.println("Average of all students: " + sum/numStudents);
			br.close();
		}
		catch (IOException ex){
			System.out.println("Can't open that file");
		}	
	}
}