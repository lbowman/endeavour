package com.levi.endeavour.gateway;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Set;

import com.levi.endeavour.state.PowerState;

public class SimpleSocket implements Gateway {
	
	public String name;
	public String connectionType;
	public String host;
	public int port;
	public String term;
	public Set<Command> commands;
	
	private Socket socket;
	private BufferedReader reader;
	private Writer writer;
	
	
	
	@Override
	public void connect() throws UnknownHostException, IOException {
		socket = new Socket(host, port);
		socket.setKeepAlive(true);
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer = new OutputStreamWriter(socket.getOutputStream());
	}
	
	@Override
	public String sendCommand(String command) throws IOException {
		writer.write(command + term);
		writer.flush();
		
		char[] chars = new char[1024];
		reader.read(chars);
		return new String(chars).trim();
	}
	
	@Override
	public PowerState getPowerState() {
		//TODO fix
		return PowerState.ON;
	}

	@Override
	public void close() throws IOException {
		socket.close();
	}
	
	@Override
	public Set<Command> getCommands() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "SimpleSocket [name=" + name + ", host=" + host + ", port=" + port + 
				", term=" + term + ", commands=" + commands + "]";
	}

	@Override
	public String getName() {
		return this.name;
	}

}
