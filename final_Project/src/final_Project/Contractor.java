package final_Project;

import java.io.IOException;

public class Contractor {

	private final String businessName, licenseNumber, address, city, state, zip, specialty, contractorName, number;
	private final int activeLicense;

	public Contractor(String businessName, String licenseNumber, String address, String city, String state, String zip,
			String number, String specialty, String contractorName, int activeLicense) {

		this.businessName = businessName;
		this.licenseNumber = licenseNumber;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.number = number;
		this.specialty = specialty; //What type of work they do.
		this.contractorName = contractorName;
		this.activeLicense = activeLicense;

	}

	public Contractor(String city, String state, String specialty) {
		this.businessName = "";
		this.licenseNumber = "";
		this.address = "";
		this.city = city;
		this.state = state;
		this.zip = "";
		this.number = "";
		this.specialty = specialty; //What type of work they do.
		this.contractorName = "";
		this.activeLicense = 1;
	}

	public boolean isActive() {
		if (this.activeLicense == 1) return true;
		else return false;
	}

	public String getLicenseNumber() {
		return this.licenseNumber;
	}

	public String getContractorName() {
		return this.contractorName;
	}

	public String getCity() {
		return this.city;
	}

	public String getState() {
		return this.state;
	}
	public String getSpecialty() {
		return this.specialty;
	}

	//Used for sorting contractors.
	public int compareTo(Contractor that) {

		if (this.isActive() != that.isActive()) {
			if(this.isActive() == true)
				return 1;
			else
				return -1;
		}

		if (this.state.compareTo(that.state) == 0)
			if (this.city.compareTo(that.city) == 0)
				return (this.specialty.compareTo(that.specialty));
			else return (this.city.compareTo(that.city));
		else return (this.state.compareTo(that.state));
	}

	public double avgReview() throws NumberFormatException, IOException {
		return Double.parseDouble(Reviews.avgOfContractor(this.getLicenseNumber()));
	}



}
