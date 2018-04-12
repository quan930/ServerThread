package quan.serverThread;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ThreadServer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = new ServerSocket(7888);
		System.out.println("Server Started!");
		List<Socket>servers = new ArrayList<Socket>();
		int i = 0;
		while(true) {
			Socket server = serverSocket.accept();
			servers.add(server);
			System.out.println(i);
			Runnable r = new TreadServerStart(server,servers,i);
			Thread t = new Thread(r);
			t.start();
			i++;
		}
	}
}
