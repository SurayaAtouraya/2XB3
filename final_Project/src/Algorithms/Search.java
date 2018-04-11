package Algorithms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ADT.Contractor;
import Read.DataReader;
import Read.Reviews;
/**
 * @author Will Donaldson, Paul Heys
 * @Version 1.0
 **/
public class Search {	
	/**
	 * Search for a contractor in sorted ArrayList of contractors
	 * @param a ArrayList<Contractor>	sorted list of contractors
	 * @param b Contractor		Contractor to be searched for
	 * @param filename String	filename of the file containing the reviews
	 * @see ArrayList
	 * @see Algorithms.Sort#ReviewSort
	 * @return ArrayList<Contractor> 	List of search hits sorted so that the top reviewed Contractors are first
	 **/
	public static ArrayList<Contractor> search(ArrayList<Contractor> a, Contractor b, String filename) throws IOException {
		Map<String,List<String>> map = Reviews.initMapFromFile(filename);
		ArrayList<Contractor> results = new ArrayList<Contractor>();
		int index = bSearch(a, b);
		b = new Contractor(b.getCity(), b.getState(), "GENERAL");
		int generalIndex = bSearch(a, b);
		if (generalIndex != -1 || index != -1) {
			int newIndex = index;
			if (index != -1) {
				while(index < (a.size() - 1) && a.get(index).compareTo(a.get(index+1)) == 0) {
					results.add(a.get(index)); 
					index++;
				}
				results.add(a.get(index));
			}
			if (generalIndex != -1 && generalIndex != newIndex) {
				while(generalIndex < (a.size() - 1) && a.get(generalIndex).compareTo(a.get(generalIndex + 1)) == 0 && generalIndex != -1) {
					results.add(a.get(generalIndex)); 
					generalIndex++;
				}
				results.add(a.get(generalIndex));
			}
			Sort.ReviewSort(results, map);
			return results;
		}
		Contractor g = new Contractor("Null", "Null", "Null");
		ArrayList<Contractor> none = new ArrayList<Contractor>();
		none.add(g);
		return none;
	}
	/**
	 *  Implementation of binary search to find (lowest) index containing a given contractor in a sorted ArrayList
	 *  @param a ArrayList<Contractor> 	sorted list of contractors to be searched
	 *  @param b Contractor		Contractor being searched for
	 *  @return int		-1 for no search hit, otherwise index in a such that a.get(index) = b and a.get(index - 1) != b
	 **/
	private static int bSearch(ArrayList<Contractor> a, Contractor b) {

		int lo = 0;
		int hi = a.size() - 1;
		while(a.get(lo).compareTo(a.get(hi)) <= 0) {
			int mid = lo + (hi - lo)/2;

			if(b.compareTo(a.get(mid)) < 0)
				hi = mid - 1;
			else if(b.compareTo(a.get(mid)) > 0)
				lo = mid + 1;
			else {
				while (mid>0 && mid < a.size() && b.compareTo(a.get(mid-1)) == 0) mid = mid -1;
				return mid;
			}
		}
		return -1;
	}
	/**
	 * Brute force implementation for finding a contractor from their license number
	 * @throws IOException 		If given file is not found
	 * @param license	license number to be searched for
	 * @return Contractor	Contractor object form data set that has the license number being searched for
	 **/
	public static Contractor searchByLicense(String license) throws IOException {
		ArrayList<Contractor> arr = DataReader.readContractors();
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i).getLicenseNumber().equals(license)) {
				return arr.get(i);
			}
		}
		return null;
	}
}
