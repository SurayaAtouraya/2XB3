package Algorithms;

import java.awt.List;
import java.io.IOException;
import java.util.Map;

import ADT.Contractor;
import Read.DataReader;
import Read.Reviews;

public class Sort {

	private static boolean less(Contractor v, Contractor w) {
		return v.compareTo(w) < 0;
	}

	static void exch(Contractor[] a, int i, int j) {
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

}
