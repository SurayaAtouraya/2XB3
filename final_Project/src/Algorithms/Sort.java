package Algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ADT.Contractor;
/**
 * @author Paul Heys, Will Donaldson
 * @version 1.0
 **/
public class Sort {

	private static Contractor[] aux;
	
	/**
	 * Compare two given contractors
	 * @param v	Contractor
	 * @param w Contractor
	 * @return boolean 		return result of compare (v < w = true, v >= w = false)
	 **/
	private static boolean less(Contractor v, Contractor w) {
		return v.compareTo(w) < 0;
	}

	/**
	 * Exchange two Contractors that are contained in an ArrayList
	 * @param a	ArrayList<Contractor>	list of contractors to have two elements exchanged within
	 * @param i	index of first contractor to be moved to the index of the second contractor
	 * @param j index of second contractor to be moved to index of first contractor
	 * 
	 **/
	static void exch(ArrayList<Contractor> a, int i, int j) {
		Contractor t  = a.get(i);
		a.set(i, a.get(j));
		a.set(j, t);
	}

	/**
	 * Determine if array list is sorted
	 * @param a	List of contractors to be checked
	 * @return boolean 	true if the array elements are sorted, false otherwise
	 **/
	public static boolean isSorted(ArrayList<Contractor> a) {
		for(int i = 1; i < a.size(); i++) {
			if(less(a.get(i), a.get(i - 1)))
				return false;
		}
		return true;
	}

	/** 
	 * API for quick sort method
	 * @see Algorithms.Sort#qSort
	 **/
	public static void sort(ArrayList<Contractor> a){

		qSort(a, 0, a.size() - 1);

	}

	/**
	 * Quick sort implementation for contractor objects
	 * @param a 	ArrayList<Contractor> to be sorted using quicksort algortihm
	 * @param lo	int index of lowest element (0 to begin)
	 * @param hi 	int index of highest element (last element to begin)
	 **/
	private static void qSort(ArrayList<Contractor> a, int lo, int hi){

		if(hi <= lo)
			return;
		int j = partition(a, lo, hi);
		qSort(a, lo, j - 1);
		qSort(a, j + 1, hi);
	}
	/**
	 * partition function for quicksort
	 * @see Algorihms.Sort#qSort
	 **/
	private static int partition(ArrayList<Contractor> a, int lo, int hi){
		int i = lo;
		int j = hi + 1;

		Contractor v = a.get(lo);
		while(true){
			while(less(a.get(++i), v))
				if(i == hi) break;
			while(less(v, a.get(--j)))
				if(j == lo) break;
			if(i >= j) break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}
	
	/**
	 * less function for use by ReviewSort
	 * @see Algorithms.Sort#ReviewSort 
	 **/
		private static boolean lessReview(Contractor a, Contractor b, Map<String,List<String>> map) {
			if (a.getSpecialty().compareTo(b.getSpecialty()) != 0) return false;
			if (a.avgReview(map).equals("N/A")) {return true;}
			if (b.avgReview(map).equals("N/A")) {return false;}
			if (Double.parseDouble(a.avgReview(map)) < Double.parseDouble(b.avgReview(map))) return true;
			return false;
		}

		/**
		 * Sort contractors so that those with reviews are shown first in decending order (highest reviewed first)
		 * @param c ArrayList<Contractor> 	list of contractors
		 * @param map	Map<String, ArrayList<String>> map license numbers of reviewed contractors to their review scores
		 **/
		public static void ReviewSort(ArrayList<Contractor> c, Map<String,List<String>> map){
			aux = new Contractor[c.size()];
			sort(c, 0, c.size()-1, map);
		}
		
		/**
		 * Mergesort implementation for sorting contractors based on reviewed score
		 **/
		private static void sort(ArrayList<Contractor> c, int lo, int hi, Map<String,List<String>> map) {
			if (hi <= lo) return;
			int mid = lo + (hi - lo)/2;
			sort(c, lo, mid, map);
			sort(c, mid+1, hi, map);
			merge(c, lo, mid, hi, map);
		}
		
		/**
		 * Function to merge subarrays back together for mergesort
		 * @see Algorithms.Sort#ReviewSort
		 **/
		private static void merge(ArrayList<Contractor> c, int lo, int mid, int hi, Map<String,List<String>> map) {
			int i = lo, j = mid+1;
			for(int k = 0; k <= hi; k++)
				aux[k] = c.get(k);
			for(int k = lo; k <= hi; k++) {
				if (i > mid) c.set(k, aux[j++]);
				else if (j > hi) c.set(k, aux[i++]);
				else if (lessReview(aux[i], aux[j], map)) c.set(k, aux[j++]);
				else c.set(k, aux[i++]);
			}
		}

}
