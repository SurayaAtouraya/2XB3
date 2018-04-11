package Graphing;

import java.io.IOException;
import java.net.URL;

import java.util.*;

import org.json.*;

public class DistanceGet {


	public static String getDriveDist(String from, String to) throws JSONException, IOException{

		String key = "AIzaSyCUhPPAXtj0DcyAkaPWEZTBaHCcr36Dgqc";//

		URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins=" + from + "&destinations=" + to + "&key=AIzaSyCUhPPAXtj0DcyAkaPWEZTBaHCcr36Dgqc");
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
	    if (! obj.getString("status").equals("OK")) {}

	        JSONArray arr = obj.getJSONArray("rows");
	        String dist = arr.toString();
	        List<String> array = Arrays.asList(dist.split("\"distance\":"));
	        dist = array.get(1);
	        array = Arrays.asList(dist.split("\"text\":"));
	        dist = array.get(1);
	        array = Arrays.asList(dist.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"));
	        dist = array.get(0);
	    	System.out.println(dist); //SUBSTRING THE DISTANCE from this string
	    	//return the distance for the user

		return dist;
	}

	public static void main(String[] args) throws JSONException, IOException {
		getDriveDist("18+Luciano+Crt+Hamilton,ON,CA","19+Nevada+Crt+Brampton,ON,CA");
	}

}
