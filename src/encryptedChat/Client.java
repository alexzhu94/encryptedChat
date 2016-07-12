package encryptedChat;

import java.net.*;
import java.io.*;

public class Client implements Runnable {
	private Socket socket = null;
	private DataInputStream console = null;
	private DataOutputStream streamOut = null;

	public Client(String serverName, int serverPort) {
		System.out.println("Establishing connection. Please wait ...");
		try {
			socket = new Socket(serverName, serverPort);
			System.out.println("Connected: " + socket);
			start();
		} catch (UnknownHostException uhe) {
			System.out.println("Host unknown: " + uhe.getMessage());
		} catch (IOException ioe) {
			System.out.println("Unexpected exception: " + ioe.getMessage());
		}
		String line = "";
		while (!line.equals(".bye")) {
			try {
				line = console.readLine();
				streamOut.writeUTF(line);
				streamOut.flush();
			} catch (IOException ioe) {
				System.out.println("Sending error: " + ioe.getMessage());
			}
		}
	}

	public void start() throws IOException {
		console = new DataInputStream(System.in);
		streamOut = new DataOutputStream(socket.getOutputStream());
	}

	public void stop() {
		try {
			if (console != null)
				console.close();
			if (streamOut != null)
				streamOut.close();
			if (socket != null)
				socket.close();
		} catch (IOException ioe) {
			System.out.println("Error closing ...");
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// System.out.println("I am client" + id);
	}

	public static void main(String args[]) {
		Client client = null;
		client = new Client("127.0.0.1", 1001);
	}
}
