package final_Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TestRead {

	public static List<Contractor> readSample() {
		
		List<Contractor> contractors = new ArrayList<>();
		int bool;
		File file = new File("FullData.txt");
		try {
			Scanner sc = new Scanner(file);
			sc.nextLine(); // Get initial out of the way
			while (sc.hasNextLine()) {
				String str = sc.nextLine();
				List<String> items = Arrays.asList(str.split("\\s*,\\s*"));
				if (items.size() == 23) {
				if (!(items.get(6).equals(""))) {
				if (items.get(22).equals("ACTIVE"))
					bool = 1;
				else
					bool = 0;
				String name = items.get(20) + items.get(19);
					Contractor guy = new Contractor(items.get(0), items.get(1), items.get(4), items.get(6),
							items.get(7), items.get(8), items.get(9), items.get(15),name, bool);
					contractors.add(guy);
				}
				}
			}
			return contractors;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return contractors;
	}

	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
		List<Contractor> contractors = readSample();
		String name = contractors.get(0).getState();
		String[] states = InterfaceDatabase.readStates();
		for (int i = 0; i < states.length; i++) {
			List<String> cities = new ArrayList<>();
			for (int j = 0; j < contractors.size(); j++) {
				if (contractors.get(j).getState().equals(states[i])) {
					if (!cities.contains(contractors.get(j).getCity())) {
						cities.add(contractors.get(j).getCity());
					}
				}
			}
			String state = states[i];
			PrintWriter temp = new PrintWriter("stateData/" + state, "UTF-8");
			for (int k = 0; k < cities.size(); k++) {
				temp.println(cities.get(k));
			}
			temp.close();
		}
	}
}