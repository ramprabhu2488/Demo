package rahulShettyAcademy.data;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;



public class DataReader {
	
	//adding that into baseclass, from where we can access the method, without creating an object
	
	public List<HashMap<String, String>> getJSONData() throws IOException {
		
		
		
		/**
		 * This helps to read the data from JSON and convert that into string. 
		 * Use user.dir
		 * This is one time Implementation
		 */
		
		// reading Json To String
	String JsonContent =	FileUtils.readFileToString(new File (System.getProperty("user.dir")+"//src//test//java//rahulShettyAcademy//data//Purchase.json"), StandardCharsets.UTF_8);
	
	
	// String to HashMap -> Add new dependency called Jackson Databind
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String, String>> data = mapper.readValue(JsonContent, new TypeReference<List<HashMap<String, String>>>(){
		
	});
	
	return data;
	
	// This data now holds the JSON values in LIST format

	}

}
