package Read;

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

import ADT.Contractor;

public class DataReader {

	public static Contractor[] readContractors() {

		List<Contractor> contractors = new ArrayList<>();
		int bool;
		File file = new File("FullData.txt");
		try {
			Scanner sc = new Scanner(file);
			sc.nextLine(); // Get initial line out of the way.
			while (sc.hasNextLine()) { // For each line, split on delimiters and create contractor object.
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
								items.get(7), items.get(8), items.get(9), items.get(15), name, bool);
						contractors.add(guy);
					}
				}
			}
			Contractor[] cons = new Contractor[contractors.size()];
			for (int i = 0; i < contractors.size(); i++) {
				cons[i] = contractors.get(i);
			}
			return cons;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}