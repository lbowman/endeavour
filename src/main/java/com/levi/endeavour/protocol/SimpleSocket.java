package com.levi.endeavour.protocol;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Set;

import com.levi.endeavour.command.Command;

public class SimpleSocket implements Closeable {
	
	public String name;
	public String connectionType;
	public String term;
	public Set<Command> commands;
	
	private Socket socket;
	private BufferedReader reader;
	private Writer writer;
	private String host = "notset";
	private int port = -1;
	
	
	public SimpleSocket() { /*empty for Jackson json */ }
	
	/*
	public SimpleSocket copyBase(SimpleSocket base) {
		SimpleSocket sock = new SimpleSocket();
		sock.name = base.name;
		sock.connectionType = base.connectionType;
		sock.term = base.term;
		sock.commands = base.commands; //OK because they don't change live.
		return sock;
	}
	*/
	
	public void connect(String host, int port) throws UnknownHostException, IOException {
		this.host = host;
		this.port = port;
		socket = new Socket(host, port);
		socket.setKeepAlive(true);
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer = new OutputStreamWriter(socket.getOutputStream());
	}
	
	public String sendCommand(String command) throws IOException {
		writer.write(command + term);
		writer.flush();
		
		char[] chars = new char[1024];
		reader.read(chars);
		return new String(chars).trim();
	}

	@Override
	public void close() throws IOException {
		socket.close();
	}
	
	public Set<Command> getCommands() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "SimpleSocket [name=" + name + ", host=" + host + ", port=" + port + 
				", term=" + term + ", commands=" + commands + "]";
	}

	public String getName() {
		return this.name;
	}

}
