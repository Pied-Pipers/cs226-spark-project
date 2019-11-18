/**
 * 
 */
package edu.ucr.cs.cs226.group4.client;

import edu.ucr.cs.cs226.group4.config.WebRequestConfig;
import edu.ucr.cs.cs226.group4.response.Response;
import edu.ucr.cs.cs226.group4.utils.WebClient;

/**
 * @author sattu
 *
 */
public class TwitterClient {

	/**
	 * 
	 */
	public TwitterClient() {
		// TODO Auto-generated constructor stub
	}
	
	public Response getTwitterData() {
		Response response = null;
		WebClient webClient = new WebClient();
		WebRequestConfig webRequestConfig = new WebRequestConfig();	
		webRequestConfig.setURL("https://api.twitter.com/1.1/tweets/search/30day/staging.json");
		webRequestConfig.addHeader("authorization", "Bearer AAAAAAAAAAAAAAAAAAAAAFh3AwEAAAAAKa5uhdaJyiy%2BUauYqCxkBgM2320%3DxNMezwTsqVWP3zQdj1GQ0FrGzJKtWgC8jR0f37ig9yGtAQrzYo");
		webRequestConfig.addHeader("header", "application/json");
		String body = "{ \"query\":\"from:narendramodi lang:en\", \"maxResults\": \"100\" }";
		webRequestConfig.setBody(body);
		
		try {
			response = webClient.doPost(webRequestConfig);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(":::: ERROR : error occurred in getTwitterData() ::::");
			e.printStackTrace();
		}

		return response;
		
	}

}
