package com.appengine;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.appengine.model.Message;
import com.appengine.model.MessageReplyRequest;
import com.appengine.utils.JSONTool;

public class HttpURLConnectionExample {


	public static void main(String[] args) throws Exception {

		HttpURLConnectionExample http = new HttpURLConnectionExample();

		//http.sendGet();
		//http.sendPost(JSONTool.writeJSON(messageReplyRequest));
		
		
	}

	// HTTP GET request
	public void sendGet() throws Exception {

		String url = "http://festive-cistern-197604.appspot.com/api/LineChat/TLJS";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		//con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}

	// HTTP POST request
	public void sendPost(String body) throws Exception {

		String url = "https://api.line.me/v2/bot/message/reply";
		URL obj = new URL(url);
		//HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Authorization", "Bearer rTeZbz5F1JtwqRNmc3CVdZIR/Sz58wjRksXogigbVI4xRmn8bklpxNZWxoPZvEiZgRGlt7PncUhh96qraOyjlYW7uyLyXIZqLFB+vAzftYCIRU/yBLG/JgWwIDNp/1PMbuPVtsc7UHoUrlC6BbPYfAdB04t89/1O/w1cDnyilFU=");

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(body);
		wr.flush();
		wr.close();

//		int responseCode = con.getResponseCode();

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}

}