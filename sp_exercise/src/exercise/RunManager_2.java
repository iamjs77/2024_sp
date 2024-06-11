package exercise;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.lgcns.test.dto.ServerRequest;

public class RunManager {

	public static Map<String, String> map = new HashMap<>();

	public static void main(String[] args) throws Exception {
		// 1. 엣지노드 로딩
		init();

		// 2. 서버 코맨드 수신, single
		Scanner scanner = new Scanner(System.in);

		String param = scanner.nextLine();

		String[] paramArr = param.split("#");
		ServerRequest sReq = new ServerRequest(paramArr);

		// 3. 디바잉스로 포워딩 ( 콘솔 출력)
		receiveServerCommand(sReq);

		// 5. readResponseFromDevice;
		Thread.sleep(500);
		List<String> resDevices = readResponseFromDevice(sReq);
		
		//6 responseToServer
		responseToServer(resDevices);

	}

	
	private static void responseToServer(List<String> resDevices) {
//		StringBuilder sb = new StringBuilder(); 
//		for(String res : resDevices) {
//			sb.append(res).append(",");
//		}
//		
//		System.out.println(sb.toString().substring(0, sb.toString().lastIndexOf(",")));
		
		System.out.println(String.join(",", resDevices));
	}

	private static List<String> readResponseFromDevice(ServerRequest sReq) throws Exception {
		// read res file
		// response order targetdevices 
		List<String> resDevice = new ArrayList<String>();
		File root = new File("DEVICE");
		for(String deviceName :sReq.targetList) {
			File[] files = root.listFiles(new FileFilter() {
				
				@Override
				public boolean accept(File pathname) {
					return (pathname.getName().startsWith("RES_FROM_" + deviceName) );
				}
			});
			
			resDevice.add(Files.readAllLines(files[0].toPath()).get(0));
		}
		
		return resDevice;
	}

	private static void receiveServerCommand(ServerRequest sReq) throws Exception {
		List<String> targetList = sReq.targetList;

		for (String target : targetList) {
			// CMD_001#DEVICE_069#fe303904
			forwardTarget(sReq.command, target, sReq.parameter);
		}

	}

	// 4. device로 전송(파일 출력으로 변경)
	private static void forwardTarget(String command, String device, String parameter) throws Exception {
		// DEVICE_069:CMD_001_A#fe303904
		// REQ_TO_<Device>.TXT
		String content = String.format("%s#%s", map.get(command), parameter);

//		System.out.println(console);
		String fName = String.format("REQ_TO_%s.TXT", device);
		File reqFile = new File("./DEVICE", fName);
		
		Files.write(reqFile.toPath(), content.getBytes(), StandardOpenOption.CREATE);
	}

	private static void init() throws Exception {
		// servercommand 로딩 후 저장
		File file = new File("./INFO", "SERVER_COMMAND.TXT");

		List<String> commands = Files.readAllLines(file.toPath());

		// "CMD_001#CMD_001_A"
		for (String command : commands) {
			String[] commadArr = command.split("#");

			map.put(commadArr[0], commadArr[1]);

		}

	}

}
