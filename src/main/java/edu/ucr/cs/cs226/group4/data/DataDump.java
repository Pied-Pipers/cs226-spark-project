/**
 * 
 */
package edu.ucr.cs.cs226.group4.data;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ucr.cs.cs226.group4.config.TwitterDataConfig;

/**
 * @author sattu
 *
 */
public class DataDump {

	/**
	 * 
	 */
	public DataDump() {
		// TODO Auto-generated constructor stub
	}

	public void dataDumpToLocalFileSystem(String filePath, TwitterDataConfig twitterDataConfig) {
		// ObjectMapper mapper = new ObjectMapper();
		FileWriter fileWriter = null;
		try {
			File file = new File(filePath);
			file.createNewFile();
			fileWriter = new FileWriter(filePath, true);
			fileWriter.write(twitterDataConfig.getProductName() + "," + twitterDataConfig.getLocation() + ","
					+ twitterDataConfig.getData().replaceAll("[^a-zA-Z]", " ").toLowerCase() + System.lineSeparator());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void dumpRawDataToLocalFileSystem(String filePath, String response) {
		ObjectMapper mapper = new ObjectMapper();
		FileWriter fileWriter = null;
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
				fileWriter = new FileWriter(filePath);
				JSONObject respObject = new JSONObject();
				respObject.put("1", response);
				// fileWriter.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(respObject));
				fileWriter.write(respObject.toJSONString());
			} else {
				FileReader fileReader = new FileReader(filePath);
				JSONObject respObject = (JSONObject) new JSONParser().parse(fileReader);
				Integer size = respObject.size() + 1;
				respObject.put(size.toString(), response);
				fileReader.close();
				fileWriter = new FileWriter(filePath);
				// fileWriter.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(respObject));
				fileWriter.write(respObject.toJSONString());
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
