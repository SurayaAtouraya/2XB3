package final_Project;

import java.util.Scanner;

public class MainPage {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("PROGRAM NAME GOES HERE");
		System.out.println();
		System.out.println("[1]: Examine Contractors.");
		System.out.println("[0]: Exit Program.");
		int input = sc.nextInt();
		
		switch(input) {
		case 0:
			System.exit(0);
			break;
		case 1:
			contractorPage();
			break;
		}
	}
	
	private static void contractorPage() {
		
	}

}
