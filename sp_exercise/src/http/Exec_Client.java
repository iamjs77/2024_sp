package http;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpMethod;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Input 폴더의 파일 목록을 전송하여 Output폴더에 저장
- Client는 목록 전송 완료 후 종료
- Server는 목록을 수신하여 ‘수신시간.json’파일로 저장하고 다시 Client 접속 대기
- 요청 Method는 ‘POST’사용
- Content Type은 “application/json” 사용
 *
 *{
	"Folder": "Input",
	"Files": [
	"close_x.png",
	"config.js",
	"desktop.js",
	"japanese_over.png",
	"main-cef-mac.css",
	"main-cef-ui-theme.css",
	"MyAll.txt",
	"November Rain.txt",
	"progress_bg_center.bmp",
	"progress_bg_left.bmp",
	"progress_bg_right.bmp",
	"progress_center.bmp",
	"progress_left.bmp",
	"progress_right.bmp",
	"test.exe"
	]
	}
 */
public class Exec_Client {

	private static final String RESOURCE_ROOT = "/http";
	
	public static void main(String[] args) throws Exception {
		HttpClient httpClient = new HttpClient();
		httpClient.start();
		
		//Request
		Request req = httpClient.newRequest("http://127.0.0.1:8088/fileList");
		req.header(HttpHeader.CONTENT_TYPE, "application-json");
		// data
		String data = getFileList();
		req.content(new StringContentProvider(data), "UTF-8"); 
		
		ContentResponse res = req.method(HttpMethod.POST).send();
		
		System.out.println(res.getContentAsString());
		System.exit(0);
	}

	private static String getFileList() throws Exception {
		JsonObject obj = new JsonObject(); 
		
		File dir = new File(RESOURCE_ROOT, "input");
		
		obj.addProperty("FOLDER", dir.getName());
		
		
		List<File> filesInFolder = Files.walk(Paths.get("./http/input"))
		        .filter(Files::isRegularFile)
		        .map(Path::toFile)
		        .collect(Collectors.toList());
		
		JsonArray array = new JsonArray(); 
		
		for(File file : filesInFolder) {
			array.add(file.getName());
		}
		
		obj.add("Files", array);
		
		return obj.toString();
	}
}
