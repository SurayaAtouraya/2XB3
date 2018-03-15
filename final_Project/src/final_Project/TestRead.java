package final_Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TestRead {

	public static List<Contractor> readSample() {
int test = 0;
		List<Contractor> contractors = new ArrayList<>();
		int bool;
		File file = new File("testfile");
		try {
			Scanner sc = new Scanner(file);
			sc.nextLine(); // Get initial out of the way
			while (sc.hasNextLine()) {
				String str = sc.nextLine();
				List<String> items = Arrays.asList(str.split("\\s*,\\s*"));
				System.out.println(test++);
				if (items.get(22).equals("ACTIVE"))
					bool = 1;
				else
					bool = 0;
				String name = items.get(20) + items.get(19);
					Contractor guy = new Contractor(items.get(0), items.get(1), items.get(4), items.get(6),
							items.get(7), items.get(8), items.get(9), items.get(15),name, bool);
					contractors.add(guy);
			}
			for (int i = 0; i < contractors.size(); i++) {
				System.out.println(contractors.get(i).getContractorName());
			}
			return contractors;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return contractors;
	}

	public static void main(String[] args) {
		readSample();
	}
}