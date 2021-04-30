package com.github.parsad23.motogpapi.reader;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

class JsonReader {

	/**
	 *
	 * @param url URL of the JSON to request
	 * @param refererUrl Header attribute
	 * @param originUrl Header attribute
	 * @return String
	 * @throws IOException
	 */
	public static String readJsonFromUrl(String url, String refererUrl, String originUrl) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection)obj.openConnection();

		con.setRequestProperty("User-Agent", " Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:75.0) Gecko/20100101 Firefox/75.0");
		con.setRequestProperty("Referer", refererUrl);
		con.setRequestProperty("Origin", originUrl);
//		con.setRequestProperty("Accept", "*/*");
//		con.setRequestProperty("Accept-Language", "it-IT,it;q=0.8,en-US;q=0.5,en;q=0.3");
//		con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
		con.setRequestProperty("Connection", "keep-alive");
//		con.setRequestProperty("Cache-Control", "max-age=0");

		//int responseCode = con.getResponseCode();
		//System.out.println("\nSending 'GET' request to URL : " + url);
		//System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
	}
}
