package org.microemu.cldc.classpath;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.microedition.io.InputConnection;

import org.microemu.microedition.io.ConnectionImplementation;

public class Connection implements InputConnection, ConnectionImplementation {

	protected String name;

	public javax.microedition.io.Connection openConnection(String name,
			int mode, boolean timeouts) throws IOException {

		this.name = name.substring("classpath:".length());

		return this;
	}

	public void close() throws IOException {
	}

	public DataInputStream openDataInputStream() throws IOException {
		return new DataInputStream(getClass().getResourceAsStream(name));
	}

	public InputStream openInputStream() throws IOException {
		return getClass().getResourceAsStream(name);
	}

}
