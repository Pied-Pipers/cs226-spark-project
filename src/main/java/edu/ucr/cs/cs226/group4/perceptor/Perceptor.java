/**
 * 
 */
package edu.ucr.cs.cs226.group4.perceptor;

import edu.ucr.cs.cs226.group4.client.TwitterClient;
import edu.ucr.cs.cs226.group4.response.Response;

/**
 * @author sattu
 *
 */
public class Perceptor {

	/**
	 * 
	 */
	public Perceptor() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TwitterClient twitterClient = new TwitterClient();
		Response response = twitterClient.getTwitterData();
		
		// Printing Twitter Data
		System.out.println(response.getResponse());
	}

}
