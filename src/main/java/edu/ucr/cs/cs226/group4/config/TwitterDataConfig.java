/**
 * 
 */
package edu.ucr.cs.cs226.group4.config;

/**
 * @author sattu
 *
 */
public class TwitterDataConfig {

	String productName = null;
	String location = null;
	String data = null; 
	/**
	 * 
	 */
	public TwitterDataConfig() {
		// TODO Auto-generated constructor stub
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProductName() {
		return this.productName;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getData() {
		return this.data;
	}

}
