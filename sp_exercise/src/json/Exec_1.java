package json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Exec_1 {

//	"{\r\n"
//	+ "\"name\":\"spiderman\",\r\n"
//	+ "\"age\":45,\r\n"
//	+ "\"married\":true,\r\n"
//	+ "\"specialty\":[\"martial art\", \"gun\"],\r\n"
//	+ "\"vaccine\":{\"1st\":\"done\",\"2nd\":\"expected\",\"3rd\":null},\r\n"
//	+ "\"children\": [{\"name\":\"spiderboy\", \"age\":10}, {\"name\":\"spidergirl\", \"age\":8}],\r\n"
//	+ "\"adress\":null\r\n"
//	+ "}\r\n"
//	+ "";
	
	public static void main(String[] args) throws Exception {
		JsonObject json = new JsonObject(); 
		json.addProperty("name", "spiderman");
		json.addProperty("age", 45);
		json.addProperty("married", true);
		
		JsonArray arr = new JsonArray(); 
		arr.add("martial art");
		arr.add("gun");
		json.add("specialty", arr);
		
		JsonObject obj1 = new JsonObject();
		obj1.addProperty("1st", "done");
		obj1.addProperty("2nd", "expected");
		obj1.add("3rd", null);
		json.add("vaccine", obj1);
		
//		+ "\"children\": [{\"name\":\"spiderboy\", \"age\":10}, {\"name\":\"spidergirl\", \"age\":8}],\r\n"
	//
		JsonArray arr2 = new JsonArray(); 
		JsonObject obj2 = new JsonObject(); 
		obj2.addProperty("name", "spiderboy");
		obj2.addProperty("age", 10);
		arr2.add(obj2);
		
		JsonObject obj3 = new JsonObject(); 
		obj3.addProperty("name", "spidergirl");
		obj3.addProperty("age", 8);
		arr2.add(obj3);
		
		json.add("children", arr2);
		
		json.add("adress", null);
		
		
		File file = new File("./json", "sample.json");
		FileWriter fileWriter = new FileWriter(file, true);
		
		System.out.println(json.toString());
		fileWriter.write(json.toString());
		fileWriter.flush();
		fileWriter.close();
		
	}
	
}
