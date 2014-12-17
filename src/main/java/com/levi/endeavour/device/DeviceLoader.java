package com.levi.endeavour.device;

import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.levi.endeavour.command.CommandSet;

public class DeviceLoader {

	private final Set<CommandSet> commandSets;
	private final String path;
	private final ObjectMapper mapper = new ObjectMapper();
	
	
	public DeviceLoader(Set<CommandSet> commandSets, String path) {
		super();
		this.commandSets = commandSets;
		this.path = path;
	}
	
	
	
}
