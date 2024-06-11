package exercise;

import java.io.File;
import java.nio.file.Files;
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

	}

	private static void receiveServerCommand(ServerRequest sReq) {
		List<String> targetList = sReq.targetList;

		for (String target : targetList) {
			// CMD_001#DEVICE_069#fe303904
			forwardTarget(sReq.command, target, sReq.parameter);
		}

	}

	// 4. device로 전송( 콘솔 출력)
	private static void forwardTarget(String command, String target, String parameter) {
		// DEVICE_069:CMD_001_A#fe303904
		String console = String.format("%s:%s:%s", target, map.get(command), parameter);

		System.out.println(console);
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
