package final_Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TestRead {

	public static void main(String[] args) {

		List<Contractor> contractors = new ArrayList<>();
		boolean bool;
		File file = new File("testfile");
		try {
			Scanner sc = new Scanner(file);
			sc.nextLine(); // Get initial out of the way
			while (sc.hasNextLine()) {
				String str = sc.nextLine();
				List<String> items = Arrays.asList(str.split("\\s*,\\s*"));
				if (items.get(22).equals("ACTIVE"))
					bool = true;
				else
					bool = false;
				
				for (int i = 0; i < items.size(); i++) {
					Contractor guy = new Contractor(items.get(0), items.get(1), items.get(4), items.get(6),
							items.get(7), items.get(8), items.get(9), bool);
					contractors.add(guy);
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(contractors.get(3).isActive());
	}

}