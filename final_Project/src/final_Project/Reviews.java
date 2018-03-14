package final_Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
		sc.close();
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
	
	public static void main(String[] args) throws IOException {
		Map<String, List<String>> map = initMapFromFile("Reviews.txt");
		writeMapToFile(map,"Reviews.txt");
	}
}
