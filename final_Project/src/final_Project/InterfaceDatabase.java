package final_Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//Used to read database to use in interface.
public class InterfaceDatabase {

	protected static String[] readStates() {
		int count = 0;
		String[] states = new String[50];
		File file = new File("stateData/states");
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				states[count] = sc.nextLine();
				count++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Arrays.sort(states);
		return states;
	}
	
	protected static String[] readCities(String state) {
		List<String> cities = new ArrayList<>();
		File file = new File("stateData/" + state);
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				cities.add(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Collections.sort(cities);
		String[] cities2 = new String[cities.size()];
		for (int i = 0; i < cities.size(); i++) {
			cities2[i] = cities.get(i);
		}
		return cities2;
	}
	
	protected static String[] readSpecialities() {
		List<String> specialities = new ArrayList<>();
		File file = new File("stateData/specialities");
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				specialities.add(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Collections.sort(specialities);
		String[] specialities2 = new String[specialities.size()];
		for (int i = 0; i < specialities.size(); i++) {
			specialities2[i] = specialities.get(i);
		}
		return specialities2;
	}
}
