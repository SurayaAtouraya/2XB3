import java.io.IOException;
import java.net.URL;

import java.util.*;

import org.json.*;

public class DistanceGet {


	public static long getDriveDist(String addrOne, String addrTwo) throws JSONException, IOException{

		String key = "AIzaSyCUhPPAXtj0DcyAkaPWEZTBaHCcr36Dgqc";

		URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins=50+Linden+Cres+Brampton,ON,CA&destinations=19+Nevada+Crt+Brampton,ON,CA&key=AIzaSyCUhPPAXtj0DcyAkaPWEZTBaHCcr36Dgqc");
		// read from the URL

		//URL use address user inputs and the contractor address to modify the
		//url string as needed, no spaces, + instead of spaces (eg
		//New+York+City,NY,USA
	    Scanner scan = new Scanner(url.openStream());
	    String str = new String();
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();

	    // build a JSON object
	    JSONObject obj = new JSONObject(str);
	    if (! obj.getString("status").equals("OK")) {

	    }

	    	String distance = obj.getString("rows");
	    	System.out.println(distance); //SUBSTRING THE DISTANCE from this string
	    	//return the distance for the user

		return 0;
	}

	public static void main(String[] args) throws JSONException, IOException {
		getDriveDist("a","s");
	}

}
