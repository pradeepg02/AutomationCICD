package seleniummaventest.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>> getJsontoMap() throws IOException
	{    
		//reading Json and coverting JSON to String using Fileutils
		String JsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\seleniummaventest\\data\\Purchaseorder.json"), StandardCharsets.UTF_8);  //The File readability format standartcharsets.. is used to restrict deprication.
		
		//Changing the string to hashmap using Jackson databind(This is exteranl as there's no change internal change method)

		  ObjectMapper mapper = new ObjectMapper();   //This is object to change the JSOn to Map
		  List<HashMap<String, String>> data = mapper.readValue(JsonContent, new TypeReference<List<HashMap<String, String>>>() {  //to read value from String and giving that JSON and how we have to convert it  - coverting to list of hashmaps
	      });
		  return data; //returning that data we converted
	}

}
