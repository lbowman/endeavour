package com.levi.endeavour.api;

import java.io.Closeable;
import java.io.IOException;
import java.net.UnknownHostException;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;
import org.glassfish.grizzly.http.server.ServerConfiguration;

import com.levi.endeavour.protocol.SimpleSocket;

public class ApiServer implements Runnable, Closeable {
	
	private final HttpServer server;
	private final ServerConfiguration serverConfiguration;
	
	public ApiServer(int port) throws UnknownHostException, IOException {
		server = HttpServer.createSimpleServer(); 
		serverConfiguration = server.getServerConfiguration();
		
		
		//TODO remove this
		addHandler(new MyHandler().connect(), "/api/marantz");
	}
	
	public void addHandler(HttpHandler handler, String path) {
		serverConfiguration.addHttpHandler(handler, path);
	}
	
	@Override
	public void run() {
		try {
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws IOException {
		server.shutdownNow();
	}
	
	
	static class MyHandler extends HttpHandler {
		SimpleSocket socket = new SimpleSocket();
		
		public MyHandler connect() throws UnknownHostException, IOException {
			socket.term = "\r";
			socket.connect("10.0.0.9", 23);
			return this;
		}
		
		@Override
		public void service(Request request, Response response) throws Exception {
			String query = request.getQueryString();
			String resp = socket.sendCommand(query);
			response.setContentType("text/plain");
            response.setContentLength(resp.length());
            response.getWriter().write(resp);
			
		}
		
	}


	
}
