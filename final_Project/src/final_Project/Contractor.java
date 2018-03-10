package final_Project;

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
	
	//Used for sorting contractors.
	public int compareTo(Contractor that) {
		if (this.state.compareTo(that.state) == 0)
			if (this.city.compareTo(that.city) == 0)
				return (this.specialty.compareTo(that.specialty));
			else return (this.city.compareTo(that.city));
		else return (this.state.compareTo(that.state));
	}
}