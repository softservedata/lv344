package com.softserve.edu;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ApplRest {

    public String getJson() throws MalformedURLException, IOException {
		// String partners =
		// " http://ssdp.qubstudio.com/api/v1/object/list/bfe3cd2467d6acc3cab8406ecce52470/";
		// String category =
		// " http://ssdp.qubstudio.com/api/v1/category/list/bfe3cd2467d6acc3cab8406ecce52470/";
		// String city =
		// " http://ssdp.qubstudio.com/api/v1/city/list/bfe3cd2467d6acc3cab8406ecce52470/";
		// String partners =
		// "https://softserve.ua/api/v1/object/list/bfe3cd2467d6acc3cab8406ecce52470/";
		// String category =
		// "https://softserve.ua/api/v1/category/list/bfe3cd2467d6acc3cab8406ecce52470/";
		// String city =
		// "https://softserve.ua/api/v1/city/list/bfe3cd2467d6acc3cab8406ecce52470/";

//		String partners = "http://api.softserve.ua/discount/api/v1/city/list/ebb8272cc8c6e25372a5a84d9314ab4f/";
//		String category = "http://api.softserve.ua/discount/api/v1/category/list/ebb8272cc8c6e25372a5a84d9314ab4f/";
//		String city = "http://api.softserve.ua/discount/api/v1/object/list/ebb8272cc8c6e25372a5a84d9314ab4f/";
//		String postal = "http://ua-postalcodes.rhcloud.com/api/regions";
		//String github = "https://api.github.com/users/nikita-pivovarov";
    	String github = "https://api.github.com/orgs/dotnet/repos";
    	//String github = "https://api.github.com/users/softservedata";
    	String heroku = "http://schedule-lv215.herokuapp.com/meetings/restByRoom";
    	//String heroku = "https://schedule-lv215.herokuapp.com/meetings/restByRoom?start=2017-02-14&end=2017-02-14&roomId=100"; 
    	String localHost = "http://localhost:8080/";
    	
		String resultString;
		//URL url = new URL(heroku);
		//URL url = new URL(github);
		URL url = new URL(localHost);
        //URL url = new URL(city);
		//URL url = new URL(partners);
		URLConnection connection = url.openConnection();
		HttpURLConnection httpConnection = (HttpURLConnection) connection;
		//httpConnection.setRequestMethod("POST");
		httpConnection.setRequestMethod("GET");
		connection.setReadTimeout(15000);
		// httpConnection.setRequestProperty("lng", "en");
		//httpConnection.setRequestProperty("changed", "1403802820");
		httpConnection.setRequestProperty("start", "2017-02-14");
		httpConnection.setRequestProperty("end", "2017-02-14");
		httpConnection.setRequestProperty("roomId", "100");
		httpConnection.setDoOutput(true);
		httpConnection.setDoInput(true);
		httpConnection.connect();
		//OutputStream os = httpConnection.getOutputStream();
		//String str = "changed=1403803787";
        //String str = "changed=0026183574";
		//String str = "changed=1403803788";
		//String str = "?start=2017-02-14";
		//str += "&end=2017-02-14";
		//str += "&roomId=100";
		//os.write(str.getBytes());
		//os.flush();
		//os.close();
		int responseCode = httpConnection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			InputStream in = httpConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(in, "UTF-8");
			// InputStreamReader isr = new InputStreamReader(in,
			// "windows-1251");
			StringBuffer data = new StringBuffer();
			int c;
			while ((c = isr.read()) != -1) {
				data.append((char) c);
			}
			resultString = new String(data.toString());
		} else {
			resultString = "Server does not respond";
		}
		httpConnection.disconnect();
		return resultString;
	}

	public static void main(String[] args) {
		ApplRest appl = new ApplRest();
		try {
			System.out.println(appl.getJson());
		} catch (MalformedURLException ex) {
			System.out.println("\nMalformedURLException:" + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("\nIOException:" + ex.getMessage());
		}
		System.out.println("\nThe End");
	}
}