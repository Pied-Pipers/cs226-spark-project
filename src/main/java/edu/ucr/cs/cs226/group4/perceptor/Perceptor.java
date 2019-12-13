/**
 * 
 */
package edu.ucr.cs.cs226.group4.perceptor;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import edu.ucr.cs.cs226.group4.client.TwitterClient;
import edu.ucr.cs.cs226.group4.config.TwitterDataConfig;
import edu.ucr.cs.cs226.group4.config.TwitterPaginationConfig;
import edu.ucr.cs.cs226.group4.data.DataDump;
import edu.ucr.cs.cs226.group4.response.Response;
import edu.ucr.cs.cs226.group4.utils.Config;
import edu.ucr.cs.cs226.group4.utils.Parser;

/**
 * @author sattu
 *
 */
public class Perceptor {

	static Long upperBoundonHttpRequests = null;
	static String absolutePathForFilteredData = null;
	static String absolutePathForRawData = null;

	/**
	 * @throws ParseException
	 * @throws IOException
	 * 
	 */
	public Perceptor() throws IOException, ParseException {
		// TODO Auto-generated constructor stub
		Config config = new Config("/Users/sattu/eclipse-workspace/edu.ucr.cs.cs226.group4/appConfig.json");
		upperBoundonHttpRequests = (Long) config.getValueForKey("upperBoundonHttpRequests");
		absolutePathForFilteredData = (String) config.getValueForKey("absolutePathForFilteredData");
		absolutePathForRawData = (String) config.getValueForKey("absolutePathForRawData");
	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException, IOException {
		// TODO Auto-generated method stub
		
		Perceptor perceptor = new Perceptor();
		String token = null;

		for (int i = 0; i < upperBoundonHttpRequests; i++) {

			DataDump dataDump = new DataDump();

			TwitterClient twitterClient = new TwitterClient();
			Response response = twitterClient.getTwitterData(token);

			dataDump.dumpRawDataToLocalFileSystem(absolutePathForRawData, response.getResponse());

			Parser pp = new Parser();
			TwitterPaginationConfig twitterPaginationConfig = pp.thirtyDayResponseParse(response.getResponse());

			ArrayList<TwitterDataConfig> twitterDataConfigList = twitterPaginationConfig.getTwitterData();
			token = twitterPaginationConfig.getToken();

			for (TwitterDataConfig twitterDataConfig : twitterDataConfigList) {
				dataDump.dataDumpToLocalFileSystem(absolutePathForFilteredData, twitterDataConfig);
			}

		}
	}

}
