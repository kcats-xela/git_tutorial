/****************************************
   Author  :  SeungTack Baek, Alex Chung
   Date    :  May 29, 2015
*****************************************/

/*******************************************************************
 * Revision History
 ----------------------------------------
 Version 1: Created ProductFileReader
 Version 2: a. Add new feature to read filename from args
            b. Read file is now pipe-delimited format
            c. Handle exception for parseDouble
            d. Improve efficiency by adding grade as you read file
            e. Include "finally" clause  
            f. Decompose methods for high cohesion
               -> Not sure what is the "best" way to do this.
               -> I feel this is still low cohesion even after revision.
********************************************************************/


package com.ydochung.kx.loader.product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProductFileReader {
	public static void main(String[] args) {
		if (args.length > 0){
			String inputFile = args[0];
			analyizeData(inputFile);
		}
		else{
			System.out.println("Invalid command line");
			System.exit(1);			
		}
	}

	/*
	 * Name: analyizeData
	 * Description: Read a pipe-limited formatted file and extract grades 
	 *              to calculate average
	 * */
	private static void analyizeData(String filename){
		double sum = 0;
		int numStudents = 0;
		try{			
			BufferedReader br = new BufferedReader(new FileReader(filename));
			try{
				String oneLine;
				while ((oneLine = br.readLine()) != null){
					String[] data = oneLine.split("\\|");
					sum = calculateSum(data[1], sum);
					numStudents++;
				}
				double result = calculateAverage(sum, numStudents);
				System.out.println("Average is : " + result);				
			}
			finally{
				br.close();
			}
		}
		catch (IOException ex){
			System.out.println("Can't open that file");
		}
	}
	
	/*
	 * Name: calculateSum
	 * Description: Add all grades
	 * */
	private static double calculateSum(String grade, double sum){
		double d_grade;
		try{
			d_grade = Double.parseDouble(grade);			
		}
		catch (NumberFormatException e){
			System.out.println("Not a legal number.");
			d_grade = Double.NaN;
		}
		return sum+d_grade;
	}
	
	/*
	 * Name: calculateAverage
	 * Description: Calculate average
	 * */
	private static double calculateAverage(double sum, int numStudents){
		return sum / numStudents;
	}
}