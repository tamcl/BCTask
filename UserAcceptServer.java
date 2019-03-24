import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.*;

class UserAcceptServer implements Runnable{

	private ServerSocket ss;
	private static int port;
	private UserData data;
	
	private Socket s;
	private InputStreamReader in;
	private BufferedReader r;
	
	public UserAcceptServer(int port, UserData data)throws IOException{
		this.port = port;
		this.data = data;
		ss = new ServerSocket(port);
	}
	
	public void run(){
		try{
			startServer();
		}catch(Exception e){
			System.out.println("Something is wrong in Receiving Server");
		}
	}
	
	public void startServer() throws IOException{
		while(true){
			s = ss.accept();
			in = new InputStreamReader (s.getInputStream());
			r = new BufferedReader(in);
			String t = r.readLine();
			System.out.println(t);
			String[] str = t.split("\\|");
		}
	}

}
