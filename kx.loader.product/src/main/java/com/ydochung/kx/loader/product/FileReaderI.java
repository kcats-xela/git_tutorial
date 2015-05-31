/****************************************
   Author  :  Alex Chung, SeungTack Baek
   Date    :  May 29, 2015
 *****************************************/

/*******************************************************************
 * Revision History
 ----------------------------------------
 Version 1 (May 28,2015) : Created ProductFileReader
 Version 2 (May 29, 2015): a. Add new feature to read filename from args
 						   b. Read file is now pipe-delimited format
 					       c. Handle exception for parseDouble
 						   d. Improve efficiency by adding grade as you read file
 						   e. Include "finally" clause  
 						   f. Decompose methods for high cohesion
 							-> Not sure what is the "best" way to do this.
 							-> I feel this is still low cohesion even after revision.
 Version 3 (May 30, 2015): a. Added Tester.java file
 						   b. Used ArrayList to store data
 						   c. Removed part d in Version 2
 ********************************************************************/

package com.ydochung.kx.loader.product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderI {
	private List<String> studentNames;
	private List<Double> studentGrades;
	private String filename;

	public FileReaderI(String filename) {
		studentNames = new ArrayList<String>();
		studentGrades = new ArrayList<Double>();
		this.filename = filename;
		generateList();
	}
	
	// comment by baek
	public void generateList() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			try {
				String oneLine;
				while ((oneLine = br.readLine()) != null) {
					String[] divider = oneLine.split("\\|");
					studentNames.add(divider[0]);
					studentGrades.add(convertStringToDouble(divider[1], 0));
				}
			} finally {
				br.close();
			}
		} catch (IOException ex) {
			System.out.println("Can't open that file");
		}
	}

	// need to get spec
	public double convertStringToDouble(String grade, double defaulValue) {
		double d_grade;
		try {
			d_grade = Double.parseDouble(grade);
		}
		catch (NumberFormatException e) {
			System.out.println("Not a legal number.");
			d_grade = defaulValue ; // what happens when invalid grade is entered? (ignore)
		}
		return d_grade;
	}
	
	public double getAverage(){
		double sum = 0;
		
		if(studentGrades != null) {
			for(Double grade : studentGrades){ // if no null-check, NPE occurs (NullPointerException)
				sum += grade;
			}
		}
		return sum / getNumOfStudents();
	}
	
	private int getNumOfStudents(){
		return studentNames.size();
	}
}
