/**
 * 
 */
package edu.ucr.cs.cs226.group4.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.ucr.cs.cs226.group4.config.TwitterDataConfig;
import edu.ucr.cs.cs226.group4.config.TwitterPaginationConfig;

/**
 * @author sattu
 *
 */
public class Parser {

	/**
	 * 
	 */
	public Parser() {
		// TODO Auto-generated constructor stub
		
	}
	
	public TwitterPaginationConfig thirtyDayResponseParse(String response) {
		
		//FileReader file = new FileReader("/Users/sattu/twitterAPI/prod_california_raw.json");
		
		TwitterPaginationConfig twitterPaginationConfig = new TwitterPaginationConfig();
		try {
			ArrayList<TwitterDataConfig> twitterDataConfigList = new ArrayList<TwitterDataConfig>();
			String nextToken = null;
			
			JSONParser jsonParser = new JSONParser();	
			JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
			
			//Check if the nextToken exist in the response to handle pagination
			if(jsonObject.containsKey("next")) {
				nextToken = (String) jsonObject.get("next");
			}	
			
			JSONArray results = (JSONArray) jsonObject.get("results");	
			Iterator iterator = results.iterator();
			while(iterator.hasNext()) {
				TwitterDataConfig twitterDataConfig = new TwitterDataConfig();
				twitterDataConfig.setProductName("nike");
				twitterDataConfig.setLocation("california");
				JSONObject tweetObject = (JSONObject) iterator.next();
				if(tweetObject.containsKey("retweeted_status")) {
					JSONObject retweet = (JSONObject) tweetObject.get("retweeted_status");
					boolean retweetTruncatedStatus = (boolean) retweet.get("truncated");
					if(retweetTruncatedStatus) {
						JSONObject retweetExtendedTweet = (JSONObject) retweet.get("extended_tweet");
						String tweet = (String) retweetExtendedTweet.get("full_text");
						twitterDataConfig.setData(tweet);
					}else {
						String tweet = (String) retweet.get("text");
						twitterDataConfig.setData(tweet);
					}
				}else {
					boolean truncatedStatus = (boolean) tweetObject.get("truncated");
					if(truncatedStatus) {
						JSONObject extendedTweet = (JSONObject) tweetObject.get("extended_tweet");
						String tweet = (String) extendedTweet.get("full_text");
						twitterDataConfig.setData(tweet);
					}else {
						String tweet = (String) tweetObject.get("text");
						twitterDataConfig.setData(tweet);
					}
				}
				twitterDataConfigList.add(twitterDataConfig);
			}
			
			twitterPaginationConfig.setTwitterData(twitterDataConfigList);
			twitterPaginationConfig.setToken(nextToken);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("::: Error occurred in parser :::: ");
			e.printStackTrace();
		}
        return twitterPaginationConfig;
	}

}
