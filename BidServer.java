import java.util.*;
import java.lang.*;
import java.io.*;
import java.net.*;

class BidServer {
	private ServerSocket s1;
	private Socket ss;
	private InputStreamReader in;
	private BufferedReader r;
	private static int port;
	private ArrayList<User> NameList = new ArrayList<User>();
	public BidServer(int port) throws IOException{
		this.port = port;
		setupServer();
	}
	public void setupServer() throws IOException{
		s1 = new ServerSocket(8000);
		while(true){
			ss = s1.accept();
			in = new InputStreamReader (ss.getInputStream());
			r = new BufferedReader(in);
			String t = r.readLine();
			String[] str = t.split("\\|");
			processMessage(str);
		}
	}
	
	private void processMessage(String[] message){
		int code = Integer.parseInt(message[0]);
		if(code==0){
			addNameList(message);
		}else if(code==1){
			for(String M:message){
				System.out.println(M);
			}
		}
	}
	
	private void addNameList(String[] message){
		User newUser = new User(message[1]);
		newUser.UpdateInfo(message[2],Integer.parseInt(message[3]));
		NameList.add(newUser);
		System.out.println("-----[Server]-----");
		System.out.println("Welcome "+ newUser.getName() + "!");
		System.out.println("----------");
	}
	
	private void bidItem(String[] message){
		
	}
}
