package final_Project;

public class Contractor {

	private final String businessName, licenseNumber, address, city, state, zip, number;
	private boolean activeLicense;

	public Contractor(String businessName, String licenseNumber, String address, String city, String state, String zip,
			String number, boolean activeLicense) {

		this.businessName = businessName;
		this.licenseNumber = licenseNumber;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.number = number;
		this.activeLicense = activeLicense;

	}

	public boolean isActive() {
		return this.activeLicense == true;
	}
