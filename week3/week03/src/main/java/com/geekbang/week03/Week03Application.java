package com.geekbang.week03;

import com.geekbang.week03.inbound.HttpInboundServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Week03Application {

	public final static String GATEWAY_NAME = "NIOGateway";
	public final static String GATEWAY_VERSION = "3.0.0";

	public static void main(String[] args) {

		String proxyPort = System.getProperty("proxyPort","8887");

		// 这是之前的单个后端url的例子
//        String proxyServer = System.getProperty("proxyServer","http://localhost:8001");
          //  http://localhost:8888/api/hello  ==> gateway API
          //  http://localhost:8088/api/hello  ==> backend service
		// java -Xmx512m gateway-server-0.0.1-SNAPSHOT.jar  #作为后端服务

		// 这是多个后端url走随机路由的例子
		String proxyServers = System.getProperty("proxyServers","http://localhost:8801");
		int port = Integer.parseInt(proxyPort);
		System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" starting...");
		HttpInboundServer server = new HttpInboundServer(port, Arrays.asList(proxyServers.split(",")));
		System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" started at http://localhost:" + port + " for server:" + server.toString());
		try {
			server.run();
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}

}
