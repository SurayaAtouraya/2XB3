package Algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ADT.Contractor;

public class Sort {

	private static Contractor[] aux;
	
	private static boolean less(Contractor v, Contractor w) {
		return v.compareTo(w) < 0;
	}

	static void exch(ArrayList<Contractor> a, int i, int j) {
		Contractor t  = a.get(i);
		a.set(i, a.get(j));
		a.set(j, t);
	}

	public static boolean isSorted(ArrayList<Contractor> a) {
		for(int i = 1; i < a.size(); i++) {
			if(less(a.get(i), a.get(i - 1)))
				return false;
		}
		return true;
	}

	public static void sort(ArrayList<Contractor> a){

		qSort(a, 0, a.size() - 1);

	}

	private static void qSort(ArrayList<Contractor> a, int lo, int hi){

		if(hi <= lo)
			return;
		int j = partition(a, lo, hi);
		qSort(a, lo, j - 1);
		qSort(a, j + 1, hi);
	}

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
	
	//less function for use by ReviewSort 
		private static boolean lessReview(Contractor a, Contractor b, Map<String,List<String>> map) {
			if (a.getSpecialty().compareTo(b.getSpecialty()) != 0) return false;
			if (a.avgReview(map).equals("N/A")) {return true;}
			if (b.avgReview(map).equals("N/A")) {return false;}
			if (Double.parseDouble(a.avgReview(map)) < Double.parseDouble(b.avgReview(map))) return true;
			return false;
		}


		public static void ReviewSort(ArrayList<Contractor> c, Map<String,List<String>> map){
			aux = new Contractor[c.size()];
			sort(c, 0, c.size()-1, map);
		}
		
		private static void sort(ArrayList<Contractor> c, int lo, int hi, Map<String,List<String>> map) {
			if (hi <= lo) return;
			int mid = lo + (hi - lo)/2;
			sort(c, lo, mid, map);
			sort(c, mid+1, hi, map);
			merge(c, lo, mid, hi, map);
		}
		
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
