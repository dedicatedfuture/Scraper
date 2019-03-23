package com.packtpub.JavaScraping.SimpleScraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.net.*;
import java.io.*;

public class WikiScraper {
	public static void main(String[] args) {
		scrapeTopic("/wiki/Python_(mythology)");
	}

	public static void scrapeTopic(String url) {
		
		Document doc;
		try {
			doc = Jsoup.connect("https://en.wikipedia.org" + url).get();
			String title = doc.title();
			System.out.println(title);
			String contentText = doc.select("#mw-content-text > p").first().text();
			System.out.println(contentText);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static String getUrl(String url) {
		URL urlObj = null;
		try {
			urlObj = new URL(url);
		} catch (MalformedURLException e) {
			System.out.println("The url was malformed!");
			return "";
		}
		URLConnection urlCon = null;
		BufferedReader in = null;
		String outputText = "";
		try {
			urlCon = urlObj.openConnection();
			in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
			String line = "";
			while ((line = in.readLine()) != null) {
				outputText += line;
			}
			in.close();
		} catch (IOException e) {
			System.out.println("There was an error connecting to the URL");
			return "";
		}
		return outputText;
	}
}