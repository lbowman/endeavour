package com.levi.endeavour.protocol;

import java.io.IOException;

public interface Protocol<E> {
	
	void connect() throws IOException;

	void sendCommand(E command) throws IOException;
}
