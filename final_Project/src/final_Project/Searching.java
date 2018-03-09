package final_Project;

import java.util.ArrayList;
import java.util.List;

public class Searching {

	//Assuming array is sorted to provide logarithmic search time
	public List<String> searchLicenseNumber(Contractor[] Contractors, String licenseNumber) { 
		if (licenseNumber.length() != 12) {
			System.out.println("Invalid string entry!");
			return null;
		}
		List<String> matchingLicenses = new ArrayList<>();
		//TODO
		return matchingLicenses;
	}
}
