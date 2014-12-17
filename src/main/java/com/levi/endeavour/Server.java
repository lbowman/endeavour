package com.levi.endeavour;

import java.io.File;
import java.util.Set;

import com.levi.endeavour.api.ApiServer;
import com.levi.endeavour.command.CommandLoader;
import com.levi.endeavour.command.CommandSet;

public class Server {

	public static void main(String[] args) throws Exception {
		ApiServer apiServer = new ApiServer(8080);
		
		//Read json configs and add handlers.
		
		File f = new File("conf/commandsets");
		Set<CommandSet> commandSets = new CommandLoader(f.getAbsolutePath()).getConfigured();
		
		//DeviceLoader has all command sets
		//Each device contains a command set and gets a connection to 
		//a protocol, there should only be a single connection for each proto+host+port+var
		
		apiServer.run();
		System.in.read();
		apiServer.close();
		
	}
	
	
}
