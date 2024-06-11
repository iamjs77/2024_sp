package http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalTime;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

/**
 * Server는 현재 날짜와 시각을 Client로 응답하게 하시오. (요청 Method는 ‘GET’ 사용)
 * 
 * http/Input 폴더의 파일 목록을 전송하여 http/Output폴더에 저장
	- Client는 목록 전송 완료 후 종료
	- Server는 목록을 수신하여 ‘수신시간.json’파일로 저장하고 다시 Client 접속 대기
	- 요청 Method는 ‘POST’사용
	- Content Type은 “application/json” 사용
	
	{
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
public class Exec_Servlet extends HttpServlet {

	private static final String RESOURCE_ROOT = "./http";
	
	private static final long serialVersionUID = -616039916872529727L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setStatus(200);
		res.getWriter().write(new Date().toString());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		JsonObject result = new JsonObject(); 
		File dir = new File(RESOURCE_ROOT, "OUTPUT");
		
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		// 수신시간.json으로 저장 
		// 파일명 생성 
		LocalTime currentTime = LocalTime.now();
		String fileName = currentTime.getHour() +"_" + currentTime.getMinute() + ".json";
		File output = new File(dir, fileName);
		
		// 대체 코드 확인
		InputStream is = req.getInputStream(); 
		byte[] targetArray = new byte[is.available()];
	    is.read(targetArray);
		
		Files.write(output.toPath(), targetArray, StandardOpenOption.CREATE); 
		
		res.setStatus(200);
		res.getWriter().write(fileName + " saved!");
	}
}
