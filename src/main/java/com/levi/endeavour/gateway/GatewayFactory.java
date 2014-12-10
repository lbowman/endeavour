package com.levi.endeavour.gateway;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GatewayFactory {

	private final String path;
	ObjectMapper mapper = new ObjectMapper();
	
	public GatewayFactory(String path) {
		this.path = path;
	}
	
	public Map<String, Gateway> getConfigured() throws Exception {
		Map<String, Gateway> gateways = new HashMap<>();
		File base = new File(path);
		File[] dirs = base.listFiles();
		//returns the directories that map to an instance of gateway.
		for (File dir : dirs) {
			switch (dir.getName()) {
			case "simplesocket":
				gateways.putAll(getSimpleSocket(dir));
			}
		}
		return gateways;
	}
	
	private Map<String, Gateway> getSimpleSocket(File dir) throws Exception {
		Map<String, Gateway> gateways = new HashMap<>();
		for (File f : dir.listFiles()) {
			SimpleSocket s = mapper.readValue(f, SimpleSocket.class);
			gateways.put(s.getName(), s);
			System.out.println(s.toString());
			System.out.println("done");
		}
		
		return gateways;
	}
}
