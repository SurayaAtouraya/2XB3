package Algorithms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ADT.Contractor;
import Read.DataReader;
import Read.Reviews;

public class Search {	
	/*Author: William Donaldson
 	*Usage: Search for contractor
 	*Takes an arrayList of contractors (Assumed to be sorted), a contractor to be searched for, and a filename for the contractor reviews
 	*The function binary searches the array for an exact match, saves the index of the match in the 
 	*variable index, then binary searches for a contractor that fits the criteria and is a Generalist 
 	*and saves the index in generalIndex. 
 	*The number of matching elements is counted then a new array of the same size is created and the 
 	*appropriate values are assigned to it. Finally the array of matching contractors is sorted so that 
 	*the highest reviewed contractors are at the beginning while the generalists are still kept at the end.*/
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
	//Search method for contractors in the dataset, configured to always return
	//the index of the first instance of a search hit in sorted array of elements and -1 otherwise 
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
	
	public static void main(String[] args) throws IOException {

		ArrayList<Contractor> arr = DataReader.readContractors();
		Sort.sort(arr);
		Contractor s = new Contractor("ANCHORAGE", "AK", "GLAZING/GLASS");
		ArrayList<Contractor> l = search(arr, s, "Reviews");
		for(int i = 0; i < l.size(); i++) {
			System.out.print(l.get(i).getState() + " " + l.get(i).getCity() + " " + l.get(i).getSpecialty() + " "+l.get(i).getLicenseNumber()+"\n");
		}
		System.out.println(l.get(0).getLicenseNumber());

	}
}
