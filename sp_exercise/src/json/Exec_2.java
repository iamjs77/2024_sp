package json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

public class Exec_2 {

	public static void main(String[] args) throws Exception {

		File input = new File("json", "sample.json");
		
		Gson gson = new Gson(); 
		JsonReader reader = new JsonReader(new FileReader(input));
		
		JsonObject json = gson.fromJson(reader, JsonElement.class);
		
		System.out.println(json);
		System.out.println(json.get("age").getAsNumber());

		System.out.println(String.format("name(age) : %s(%d)", json.get("name").getAsString(), json.get("age").getAsInt() ));
		
	}
	
}
