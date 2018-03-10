package final_Project;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Reviews {
	private static Scanner sc;

	//Creates the reviews database if not already found.
	public static void fileCheck() throws IOException {
		File file = new File("Reviews.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
	}
	
	//Find a contractor based on license number in the reviews file, then returns
	//their reviews data in the line including license number. Line must not 
	//contain any space, use commas for separation only in reviews.txt. Returns
	// null if not found.
	public static String findContractor(String licenseNumber) throws IOException {
		fileCheck();
		File file = new File("Reviews.txt");
		sc = new Scanner(file);
		while (sc.hasNext()) {
			String line = sc.next();
			if (line.substring(0, 12).equals(licenseNumber)) {
				return line;
			}
		}
		return null;
	}
	
	//If a contractor is found, returns the average of their reviews, being the 
	//last number in the line.
	public static String avgOfContractor(String licenseNumber) throws IOException {
		String reviews = findContractor(licenseNumber);
		if (reviews != null) {
			reviews = reviews.substring(13, reviews.length());
			List<String> reviewsList = Arrays.asList(reviews.split(","));
			return reviewsList.get(reviewsList.size() - 1);
		}
		else return null;
	}
	
	public static void main(String[] args) throws IOException {
	}
}
