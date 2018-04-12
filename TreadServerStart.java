package quan.serverThread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TreadServerStart implements Runnable{
	//线程逻辑
	private Socket server;
	private List<Socket> servers;
	private int threadId; 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("线程");
		try(OutputStream outServer = server.getOutputStream();InputStream inServer = server.getInputStream()){
			Scanner in = new Scanner(inServer,"UTF-8");
			PrintWriter out = new PrintWriter(new OutputStreamWriter(outServer,"UTF-8"),true);
			out.println("已连接服务器(quit退出)");
			System.out.println("有一个客户端进行了连接");
			//out.flush();
			while(true) {
				out.println("你的Id为"+threadId);
				out.println("选择输出对象:");
//				int i = in.nextInt();
				String stringSer = in.nextLine();
				int i = Integer.parseInt(stringSer);
				Socket s = servers.get(i);
				PrintWriter outS = new PrintWriter(new OutputStreamWriter(s.getOutputStream(),"UTF-8"),true);
				out.println("输出内容:");
				//String stringSer = in.nextLine();
				stringSer = in.nextLine();
				//System.out.println("客户端:"+stringSer);
				if(stringSer.equals(new String("quit"))) {
					out.println("Bye!");
					out.flush();
					break;
				}
				outS.println(stringSer);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public TreadServerStart(Socket server,List servers,int threadId) {
		this.server = server;
		this.servers = servers;
		this.threadId = threadId;
	}
//	
//	public Socket findSocket(Socket cs,List<Socket> servers,String out) {
//        for (int i = 0; i < servers.size(); i++) {// 遍历所有的线程
//            Socket s = servers.get(i);
//            if (s != cs)// 判断不是当前线程就发送此消息
//                //s.out(out + "\n");
//        }
//    }
	
}
