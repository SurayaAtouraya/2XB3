package final_Project;

import java.io.File;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Reviews {
	private static Scanner sc;

	//Creates the reviews database if not already found, loads the map.
	public static Map<String, List<String>> initMapFromFile(String fileName) throws IOException {
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("File not found, creating file...");
			file.createNewFile();
			return null;
		}
		sc = new Scanner(file);
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		while (sc.hasNext()) {
			String line = sc.next();
			String reviews = line.substring(13, line.length());
			List<String> reviewsList = Arrays.asList(reviews.split(","));
			map.put(line.substring(0, 12),reviewsList);
		}
		return map;
	}
	
	//If a contractor is found, returns the average of their reviews, being the 
	//last number in the line.
	public static String avgOfContractor(String licenseNumber, Map <String, List<String>> map) {
		if (map.containsKey(licenseNumber)) {
		int size = map.get(licenseNumber).size();
		return map.get(licenseNumber).get(size - 1);
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		Map<String, List<String>> map = initMapFromFile("Reviews.txt");
		String avg = avgOfContractor("ABCDEFGHIJKL", map);
		System.out.println(avg);
	}
}
