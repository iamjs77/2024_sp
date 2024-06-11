package exercise;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lgcns.test.dto.Device;
import com.lgcns.test.dto.DeviceInfo;
import com.lgcns.test.dto.ServerCommand;
import com.lgcns.test.dto.ServerCommandInfo;

public class EdgeNodeServer {

	public static Map<String, String> serverMap = new HashMap<>();
	public static Map<String, DeviceInfo> deviceMap = new HashMap<>();

	public void start() throws Exception {

		init();
		
		Server server = new Server();
		ServerConnector connector = new ServerConnector(server);

		// 서버 환경 구성
		connector.setPort(8010);
		connector.setHost("127.0.0.1");
		server.addConnector(connector);

		// 서블릿 설정s
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/*");
        context.addServlet(new ServletHolder(new EdgeNodeServlet(this.serverMap, this.deviceMap)), "/*");
        server.setHandler(context);

//		ServletHandler servletHandler = new ServletHandler();
//		servletHandler.addServletWithMapping(EdgeNodeServlet.class, "/fromServer");
//		server.setHandler(servletHandler);

		// 서버 실행 및 대기
		server.start();
		server.join();

	}

	private static void init() throws Exception {
		// server command, device 로딩(json)
		// 1. server commad

		File serverCommandJson = new File("./INFO", "SERVER_COMMAND.json");

		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader(serverCommandJson));

		ServerCommand commands = gson.fromJson(reader, ServerCommand.class);

		List<ServerCommandInfo> commandInfos = commands.getServerCommandInfo();
//			// "CMD_001#CMD_001_A"
		for (ServerCommandInfo command : commandInfos) {
			serverMap.put(command.getCommand(), command.getForwardCommand());

		}

		File deviceJson = new File("./INFO", "DEVICE.json");

		JsonReader reader2 = new JsonReader(new FileReader(deviceJson));

		Device device = gson.fromJson(reader2, Device.class);

		List<DeviceInfo> deviceInfos = device.getDeviceInfo();
//			// "CMD_001#CMD_001_A"
		for (DeviceInfo deviceInfo : deviceInfos) {
			deviceMap.put(deviceInfo.getDevice(), deviceInfo);

		}
	}
}
