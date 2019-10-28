package clientServer;

import java.awt.BorderLayout;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Server extends JFrame{
	
	private JTextArea msgArea;
	private DataOutputStream toClient;
	private DataInputStream fromClient;
	private static ArrayList<Socket> clients;
	private int privKey, pubKey1, pubKey2, symKey;
	
	public void buildGUI() {
		final int FRAME_WIDTH = 700;
		final int FRAME_HEIGHT = 300;
		msgArea = new JTextArea();
		msgArea.setEditable(false);
		setLayout(new BorderLayout());
		add(new JScrollPane(msgArea), BorderLayout.CENTER);
		setTitle("Chatroom");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void diffieHellman(){
		privKey = (int)(Math.random()*((100-10)+1))+10;
		//TODO - exchange keys
	}
	
	public Server(int port) {
		buildGUI();
		try (ServerSocket server = new ServerSocket(port)){
			msgArea.append("Server started \n");
			clients = new ArrayList<Socket>();
			msgArea.append("Waiting for clients... \n");
			Socket client = server.accept();
			msgArea.append("Client accepted at: " + port + '\n');
			
			this.fromClient = new DataInputStream(new BufferedInputStream(client.getInputStream()));
			this.toClient = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
			
			doService();
			
			msgArea.append("Closing server");
			client.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	


	private void doService() {
		String buffer = "";
		while (!buffer.equals("kill")) {
			try {
				buffer = fromClient.readUTF();
				msgArea.append("Received message: " + buffer + '\n');
				toClient.writeUTF(buffer);
				toClient.flush();
				msgArea.append("Sent message \n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	}
	
	public static void main(String[] args) {
		new Server(8081);
	}

}
