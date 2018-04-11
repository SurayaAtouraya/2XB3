package ADT;

import java.util.List;
import java.util.Map;

import Read.Reviews;
/** 
 * Contractor ADT class to encapsulate all data relevant to a contractor, and to 
 * perform any operations on the contractors (compare, check for valid license, etc)
 * @author Immanuel Odisho 
 * @version 1.0
 * 
 **/
public class Contractor {

	private final String businessName, licenseNumber, address, city, state, zip, specialty, contractorName, number;
	private final int activeLicense;

	/**
	 * Constructor for Contractor object, this is the full constructor (all relevant data present in the database) 
	 **/
	
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

	/**
	 * Contractor constructor (simple version) for use with searching methods
	 * { @link Algorithms.Search }
	 **/
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
	/** 
	 * Getter for license status (active vs not active)
	 * @return boolean (true for active)
	 **/
	public boolean isActive() {
		if (this.activeLicense == 1) return true;
		else return false;
	}

	/**
	 * @return licenseNumber of this Contractor
	 **/
	public String getLicenseNumber() {
		return this.licenseNumber;
	}

	/**
	 * @return licenseNumber of this Contractor
	 **/
	public String getAddress() {
		return this.address;
	}

	/**
	 * @return licenseNumber of this Contractor
	 **/
	public String getContractorName() {
		return this.contractorName;
	}

	/**
	 * @return licenseNumber of this Contractor
	 **/
	public String getNumber() {
		return this.number;
	}

	/**
	 * @return licenseNumber of this Contractor
	 **/
	public String getCity() {
		return this.city;
	}

	/**
	 * @return licenseNumber of this Contractor
	 **/
	public String getState() {
		return this.state;
	}
	
	/**
	 * @return licenseNumber of this Contractor
	 **/
	public String getSpecialty() {
		return this.specialty;
	}

	/**
	 * Compare to implementation for contractor comparisons
	 * @param that 	contractor to be compared to the calling contractor (this)
	 * @return int 	-1 for <, 0 for =, 1 for >.
	 **/
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

	/**
	 * Implements avgOfContractor to determine the given contractor's average review
	 * score.
	 * @see Read.Reviews#avgOfContractor
	 * @param map 	map of license number of contractor (String) to list of reviews (List<String>)
	 * @return String 	average of all reviews submitted for the contractor
	 **/
	public String avgReview(Map<String, List<String>> map){
		String r = (Reviews.avgOfContractor(this.getLicenseNumber(), map));
		if (r == null) return "N/A";
		return r;
	}



}
