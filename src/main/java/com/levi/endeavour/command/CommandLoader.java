package com.levi.endeavour.command;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CommandLoader {

	private final String path;
	ObjectMapper mapper = new ObjectMapper();
	
	public CommandLoader(String path) {
		this.path = path;
	}
	
	public Set<CommandSet> getConfigured() throws Exception {
		Set<CommandSet> commandSets = new HashSet<>();
		File base = new File(path);
		File[] dirs = base.listFiles();
		for (File f : dirs) {
			CommandSet commandSet = mapper.readValue(f, CommandSet.class);
			commandSets.add(commandSet);
		}
		return commandSets;
	}
}
