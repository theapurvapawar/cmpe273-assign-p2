package testing;



import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTest {

	@JsonIgnoreProperties(ignoreUnknown = true)
	public String _id;
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}


	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}


	private String product_id;
//	private String category;
//	private String product_url;
//	private String image_url;
//	private String description;
//	private String price;
//	private String name;
//	private String type;
//	private String weight;
//	private String warranty;
//	private String no_of_blades;
//	
	

	private String startDate;
	private String endDate;
	
	

//	public String getProduct_id() {
//		return product_id;
//	}
//
//	public String getCategory() {
//		return category;
//	}
//
//	public void setCategory(String category) {
//		this.category = category;
//	}
//
//	public String getProduct_url() {
//		return product_url;
//	}
//
//	public void setProduct_url(String product_url) {
//		this.product_url = product_url;
//	}
//
//	public String getImage_url() {
//		return image_url;
//	}
//
//	public void setImage_url(String image_url) {
//		this.image_url = image_url;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public String getPrice() {
//		return price;
//	}
//
//	public void setPrice(String price) {
//		this.price = price;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}
//
//	public String getWeight() {
//		return weight;
//	}
//
//	public void setWeight(String weight) {
//		this.weight = weight;
//	}
//
//	public String getWarranty() {
//		return warranty;
//	}
//
//	public void setWarranty(String warranty) {
//		this.warranty = warranty;
//	}
//
//	public String getNo_of_blades() {
//		return no_of_blades;
//	}
//
//	public void setNo_of_blades(String no_of_blades) {
//		this.no_of_blades = no_of_blades;
//	}
//
//	public void setProduct_id(String product_id) {
//		this.product_id = product_id;
//	}


	
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
