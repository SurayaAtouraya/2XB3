package final_Project;

import java.awt.List;
import java.io.IOException;
import java.util.Map;

public class SortMe {

	private static boolean less(Contractor v, Contractor w) {
		return v.compareTo(w) < 0;
	}

	private static void exch(Contractor[] a, int i, int j) {
		Contractor t  = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static boolean isSorted(Contractor[] a) {
		for(int i = 1; i < a.length; i++) {
			if(less(a[i], a[i - 1]))
				return false;
		}
		return true;
	}

	public static void sort(Contractor[] a){

		qSort(a, 0, a.length - 1);

	}

	private static void qSort(Contractor[] a, int lo, int hi){

		if(hi <= lo)
			return;
		int j = partition(a, lo, hi);
		qSort(a, lo, j - 1);
		qSort(a, j + 1, hi);
	}

	private static int partition(Contractor[] a, int lo, int hi){
		int i = lo;
		int j = hi + 1;

		Contractor v = a[lo];
		while(true){
			while(less(a[++i], v))
				if(i == hi) break;
			while(less(v, a[--j]))
				if(j == lo) break;
			if(i >= j) break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}
	
	/*Author: William Donaldson
	 *Usage: Search for contractor
	 *Takes an array of contractors (Assumed to be sorted), a contractor to be searched for, and a filename for the contractor reviews
	 *The function binary searches the array for an exact match, saves the index of the match in the 
	 *variable index, then binary searches for a contractor that fits the criteria and is a Generalist 
	 *and saves the index in generalIndex. 
	 *The number of matching elements is counted then a new array of the same size is created and the 
	 *appropriate values are assigned to it. Finally the array of matching contractors is sorted so that 
	 *the highest reviewed contractors are at the beginning while the generalists are still kept at the end.*/
	public static Contractor[] search(Contractor[] a, Contractor b, String filename) throws IOException {
		Map map = Reviews.initMapFromFile(filename);
		int index = bSearch(a, b);
		b = new Contractor(b.getCity(), b.getState(), "GENERAL");
		int generalIndex = bSearch(a, b);

		int tempIndex = index;
		int i = 0;
		
		if (index != -1) {
			while(tempIndex < (a.length - 1) && a[tempIndex].compareTo(a[tempIndex + 1]) == 0) {
				i++;
				tempIndex++;
			}
			i++;
		}
		
		tempIndex = generalIndex;
		if (generalIndex != -1) {
			while(tempIndex != -1 && tempIndex < (a.length - 1) && a[tempIndex].compareTo(a[tempIndex + 1]) == 0) {
				i++;
				tempIndex++;
			}
			i++;
		}
		
		if (generalIndex != -1 || index != -1) {
			Contractor[] output = new Contractor[i];
			i = 0;
			if (index != -1) {
				while(index < (a.length - 1) && a[index].compareTo(a[index + 1]) == 0) {
					output[i] = a[index]; 
					index++;
					i++;
				}
				output[i++] = a[index];
			}
			if (generalIndex != -1) {
				while(generalIndex < (a.length - 1) && a[generalIndex].compareTo(a[generalIndex + 1]) == 0 && generalIndex != -1) {
					output[i] = a[generalIndex]; 
					generalIndex++;
					i++;
				}
				output[i] = a[generalIndex];
			}
			ReviewSort(output, map);
			return output;
		}
		Contractor[] none = new Contractor[1];
		return none;
	}
	//Search method for contractors in the dataset, configured to always return
	//the index of the first instance of a search hit in sorted array of elements and -1 otherwise 
	private static int bSearch(Contractor[] a, Contractor b) {

		int lo = 0;
		int hi = a.length - 1;
		while(a[lo].compareTo(a[hi]) <= 0) {
			int mid = lo + (hi - lo)/2;

			if(b.compareTo(a[mid]) < 0)
				hi = mid - 1;
			else if(b.compareTo(a[mid]) > 0)
				lo = mid + 1;
			else {
				while (b.compareTo(a[mid-1]) == 0) mid = mid -1;
				return mid;
			}
		}
		return -1;
	}
	//less function for use by ReviewSort 
	private static boolean lessReview(Contractor a, Contractor b, Map map) {
		if (a.getSpecialty().compareTo(b.getSpecialty()) != 0) return false;
		if (a.avgReview(map).equals("N/A")) {return true;}
		if (b.avgReview(map).equals("N/A")) {return false;}
		if (Double.parseDouble(a.avgReview(map)) > Double.parseDouble(b.avgReview(map))) return true;
		return false;
	}
	
	//Implementation of insertion sort that sorts contractors of the same type by average review (highest review first)
	private static Contractor[] ReviewSort(Contractor[] c, Map map) {
		int n = c.length;
		for(int i = 1; i < n; i++) {
			for(int j = i; j > 0 && lessReview(c[j], c[j-1], map); j--) {
				exch(c, j, j-1);
			}
		}
		return c;
	}


	public static void main(String[] args) throws IOException {

		Contractor[] arr = TestRead.readSample();
		sort(arr);
		Contractor s = new Contractor("SEATTLE", "WA", "LANDSCAPING");
		Contractor[] l = search(arr, s, "Reviews");
		for(int i = 0; i < l.length; i++) {
			System.out.print(l[i].getState() + " " + l[i].getCity() + " " + l[i].getSpecialty() + "\n");
		}

	}

}
