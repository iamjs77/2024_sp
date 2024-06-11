package http;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 * Client에서 Server에 “http://127.0.0.1:8088/requestDate”로 요청하고,  
 * Server는 현재 날짜와 시각을 Client로 응답하게 하시오. (요청 Method는 ‘GET’ 사용)
 *
 */
public class Exec_Server {

	public void start() throws Exception {
		Server server = new Server();	
		ServerConnector connector = new ServerConnector(server);
		
		// 서버 환경 구성
		connector.setPort(8088);
		connector.setHost("127.0.0.1");
		server.addConnector(connector);
		
		// 서블릿 설정
		ServletHandler servletHandler = new ServletHandler();
		servletHandler.addServletWithMapping(Exec_Servlet.class, "/");
		server.setHandler(servletHandler);
		
		// 서버 실행 및 대기
		server.start();
		server.join();
	}

	
}
