package com.levi.endeavour.gateway;

import java.io.Closeable;
import java.util.Set;

import com.levi.endeavour.state.PowerState;

public interface Gateway extends Closeable {
	void connect() throws Exception;
	
	PowerState getPowerState();
	
	String sendCommand(String command) throws Exception;
	
	Set<Command> getCommands();
	
	String getName();
}
