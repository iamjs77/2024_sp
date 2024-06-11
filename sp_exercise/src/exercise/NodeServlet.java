package exercise;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;

import com.google.gson.Gson;
import com.lgcns.test.dto.DeviceInfo;
import com.lgcns.test.dto.DeviceRequest;
import com.lgcns.test.dto.ServerRequest;

public class EdgeNodeServlet extends HttpServlet {

	public static Map<String, String> serverMap = new HashMap<>();
	public static Map<String, DeviceInfo> deviceMap = new HashMap<>();
	public static Gson gson = new Gson();

	public EdgeNodeServlet(Map<String, String> serverMap, Map<String, DeviceInfo> deviceMap) {
		this.serverMap = serverMap;
		this.deviceMap = deviceMap;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 2. 서버 코맨드 수신
		// Body:{"command":"CMD_001","targetDevice":["DEVICE_069"],"param":"fe303904"})
		// body json을 String 으로 변경
//		String requestJson = req.getAttribute("Body").toString();
		String requestString = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		ServerRequest sReq = gson.fromJson(requestString, ServerRequest.class);

		// 3. 디바잉스로 포워딩
		try {
			receiveServerCommand(sReq);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void receiveServerCommand(ServerRequest sReq) throws Exception {
		List<String> targetList = sReq.targetDevice;

		String commandResponse = null;
		for (String target : targetList) {
			// CMD_001#DEVICE_069#fe303904
			commandResponse = sendCommandToDevice(sReq.command, target, sReq.param);
		}

		//Response String을 json으로 변경( gson.fromJson)
		CommandResponse responseFromDevice = gson.fromJson(commandResponse, CommandResponse.class);
		result.addAll(responseFromDevice.getResult());

	}

	// 4. device로 전송(httpclien로 변경) 후 응답 수신
	private static String sendCommandToDevice(String command, String device, String parameter) throws Exception {
		// DEVICE_069:CMD_001_A#fe303904

		try {
			// httpClient로 변경
			HttpClient client = new HttpClient();
			client.start();

			// Request
			//POST http://<Device hostname>:<Device port>/fromEdge
			DeviceInfo dInfo = deviceMap.get(device);

			// URI 생성
			String uriStr = String.format("http://%s:%d/fromEdge", dInfo.getHostname(), dInfo.getPort());

			// Request 셋팅
			Request req = client.POST(uriStr);
			req.header(HttpHeader.CONTENT_TYPE, "application-json");

			// json 데이터 전송
			String jsonContents = gson.toString(new DeviceRequest(String.format("%s#%s", serverMap.get(command), parameter)));
			req.content(new StringContentProvider(jsonContents, "utf-8"));

			// device response 수신.
			ContentResponse response = request.send();
			// response의 데이터 추출
			return new String(response.getContent());
		} catch  ( ExecutionException e) {
			e.printStackTrace();
		} finally {
			client.stop
		}

		return null;
	}

//	receiveServerCommand(sReq);
//
//		// 5. readResponseFromDevice;
//		Thread.sleep(500);
//		List<String> resDevices = readResponseFromDevice(sReq);
//
//		// 6 responseToServer
//		responseToServer(resDevices);
//
//	private static void responseToServer(List<String> resDevices) {
//		StringBuilder sb = new StringBuilder();
//
//		for (String res : resDevices) {
//			sb.append(res).append(",");
//		}
//d
//		System.out.println(sb.toString().substring(0, sb.toString().lastIndexOf(",")));
//
//	}
//
//	private static List<String> readResponseFromDevice(ServerRequest sReq) throws Exception {
//		// read res file
//		// response order targetdevices
//		List<String> resDevice = new ArrayList<String>();
//		File root = new File("DEVICE");
//		for (String deviceName : sReq.targetList) {
//			File[] files = root.listFiles(new FileFilter() {
//
//				@Override
//				public boolean accept(File pathname) {
//					return (pathname.getName().startsWith("RES_FROM_" + deviceName));
//				}
//			});
//
//			resDevice.add(Files.readAllLines(files[0].toPath()).get(0));
//		}
//
//		return resDevice;
//	}
//

//

}
