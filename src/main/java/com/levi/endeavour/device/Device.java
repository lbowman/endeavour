package com.levi.endeavour.device;

import java.io.IOException;

import com.levi.endeavour.command.CommandSet;
import com.levi.endeavour.protocol.Protocol;

public class Device<E> {

	private final CommandSet commandSet;
	private final Protocol<E> protocol;
	
	
	public Device(CommandSet commandSet, Protocol<E> protocol) {
		super();
		this.commandSet = commandSet;
		this.protocol = protocol;
	}
	
	
	public void connect() throws IOException {
		protocol.connect();
	}
	
	public void sendCommand(String commandName) {
		//commands need to be a map, not a class.
	}
	
}
