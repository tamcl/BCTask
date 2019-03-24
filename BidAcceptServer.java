import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.*;

class BidAcceptServer implements Runnable{
	private ServerSocket ss;
	private static int port;
	private BidServer data;
	
	private Socket s;
	private InputStreamReader in;
	private BufferedReader r;
	
	public BidAcceptServer(int port, BidServer data) throws IOException{
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
			String[] str = t.split("\\|");
			processMessage(str);
		}
	}
	
	private void processMessage(String[] message){
		int code = Integer.parseInt(message[0]);
		if(code==0){
			addNameList(message);
		}else if(code==1){
			bidShout(message);
		}
	}
	
	private void addNameList(String[] message){
		User newUser = new User(message[1]);
		newUser.UpdateInfo(message[2],Integer.parseInt(message[3]));
		data.addName(newUser);
		System.out.println("-----[Server]-----");
		System.out.println("Welcome "+ newUser.getName() + "!");
		System.out.println("----------");
		try{
			new SendMessage(3, newUser.getIp(), newUser.getPort(), "User Entered Server");
		}catch(Exception e){
			System.out.println("message sent error");
		}
	}
	
	private void bidShout(String[] message){
		System.out.println(message[4]);
		try{
			new SendMessage(3, message[2], Integer.parseInt(message[3]), "Bid Successful, Bid cost"+message[4]);
		}catch(Exception e){
			System.out.println("message sent error");
		}
		for(User a:data.getNameList()){
			try{
				new SendMessage(4, a.getIp(), a.getPort(), "Current Highest Bid: "+message[4]);
			}catch(Exception e){
			System.out.println("something wrong");
		}
		}
	}
}
