package final_Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
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
			Map<String, List<String>> emptyMap = new HashMap<String, List<String>>();
			return emptyMap;
		}
		sc = new Scanner(file);
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		while (sc.hasNext()) {
			String line = sc.next();
			String reviews = line.substring(13, line.length());
			ArrayList<String> reviewsList = new ArrayList<String>(Arrays.asList(reviews.split("\\s*,\\s*")));
			map.put(line.substring(0, 12),reviewsList);
		}
		sc.close();
		return map;
	}
	
	//If a contractor is found, returns the average of their reviews.
	public static String avgOfContractor(String licenseNumber, Map <String, List<String>> map) {
		if (map.containsKey(licenseNumber)) {
		int size = map.get(licenseNumber).size();
		return map.get(licenseNumber).get(size - 1);
		}
		return null;
	}
	
	//Writes Map to file. (Used on program shutdown)
	public static void writeMapToFile(Map <String, List<String>> map, String fileName) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter file = new PrintWriter(fileName, "UTF-8");
		Iterator it = map.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        String vals = pair.getValue().toString();
	        vals = vals.substring(1, vals.length()-1);
	        vals = vals.replaceAll("\\s+","");
	        file.println(pair.getKey() + "," + vals);
	        it.remove();
	    }
	    file.close();
	}
	//Adds review to contractor, will add contractor to map if not found.
	public static void addReview(Map <String, List<String>> map, String licenseNumber, String review) {
		if (map.containsKey(licenseNumber)) {
			int end = map.get(licenseNumber).size();
			if (end != 0) {
				map.get(licenseNumber).add(end - 1, review);
				String newAvg = calculateAvg(map, licenseNumber);
				map.get(licenseNumber).set(end, newAvg);
			}
			else {
				map.get(licenseNumber).add(0, review);
				map.get(licenseNumber).add(1, review);
			}
		}
		else {
			addContractor(map, licenseNumber);
			addReview(map, licenseNumber, review);
		}
	}
	
	//Adds contractor to Map.
	private static void addContractor(Map <String, List<String>> map, String licenseNumber) {
		List<String> empty = new ArrayList<String>();
		map.put(licenseNumber, empty);
	}
	
	//Calculates some contractors average review rating, returns as String and rounds to one decimal
	private static String calculateAvg(Map <String, List<String>> map, String licenseNumber) {
		if (map.containsKey(licenseNumber)) {
			float sum = 0; int count = 0;
			for (String review : map.get(licenseNumber)) {
				sum += Float.parseFloat(review);
				count++;
			}
			count--;
			sum -= Float.parseFloat(map.get(licenseNumber).get(map.get(licenseNumber).size() - 1));
			sum = sum/count;
			return String.valueOf(sum = (float) (Math.round(sum * 10.0) / 10.0));
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		Map<String, List<String>> map = initMapFromFile("Reviews.txt");
		addReview(map, "123456789ABC", "5");
		writeMapToFile(map, "Reviews.txt");
	}
}
