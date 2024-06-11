package exercise;

import com.lgcns.test.server.EdgeNodeServer;

public class RunManager {

	public static void main(String[] args) throws Exception {
		EdgeNodeServer server = new EdgeNodeServer();
		
		server.start();
	}
}
