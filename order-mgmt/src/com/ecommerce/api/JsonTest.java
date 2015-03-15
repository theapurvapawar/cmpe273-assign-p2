package com.ecommerce.api;



import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTest {

	private String product_id;
	private String startDate;
	private String endDate;
	

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	@JsonAnySetter
	  public void handleUnknown(String key, Object value) {
	    // ignore; handles fields which are not required
	  }
	
	
public static void main(String[] args){
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			ArrayList<JsonTest> value = mapper.readValue(
					new URL("http://localhost:3000/api/products/product_id/fan1"),
					mapper.getTypeFactory().constructCollectionType(
		                    ArrayList.class, JsonTest.class
					)
				);
			
			System.out.println(value.get(0).getProduct_id());
			System.out.println(value.get(0).getStartDate());
			System.out.println(value.get(0).getEndDate());
		
		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
	}

}
