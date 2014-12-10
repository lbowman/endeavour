package com.levi.endeavour;

import java.io.File;
import java.util.Map;

import com.levi.endeavour.api.ApiServer;
import com.levi.endeavour.gateway.Gateway;
import com.levi.endeavour.gateway.GatewayFactory;

public class Server {

	public static void main(String[] args) throws Exception {
		ApiServer apiServer = new ApiServer(8080);
		
		//Read json configs and add handlers.
		
		File f = new File("conf/gateways");
		Map<String, Gateway> gateways = new GatewayFactory(f.getAbsolutePath()).getConfigured();
		
		
		
		apiServer.run();
		System.in.read();
		apiServer.close();
		
	}
	
	
}
