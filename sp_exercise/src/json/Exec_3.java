package json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;

public class Exec_3 {

	public static void main(String[] args) throws Exception {

		File input = new File("json", "sample.json");
		
		Gson gson = new Gson(); 
		JsonReader reader = new JsonReader(new FileReader(input));
		
		JsonObject json = gson.fromJson(reader, JsonElement.class);
		
		for(String key :  json.keySet() ) {
			
			JsonElement element = json.get(key); 
			
			if(element.isJsonPrimitive() ) {
				
				JsonPrimitive primitive = element.getAsJsonPrimitive(); 
				
				if(primitive.isBoolean()) {
					
				} else if(primitive.isNumber()) {
					
				} else if(primitive.isString()) {
					
				}
				
			} else if(element.isJsonObject()) {
				
			} else if(element.isJsonArray()) {
				
			} else if(element.isJsonNull()) {
				
			}
				
			
			
		}
		System.out.println(String.format("name(age) : %s(%s)", json.get("name").getAsString(), json.get("age").getAsNumber() ));
		
	}
	
}
