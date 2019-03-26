import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.*;

class UserAcceptServer implements Runnable{

	private ServerSocket ss;
	private static int port;
	private UserData data;
	private User user;
	
	private Socket s;
	private InputStreamReader in;
	private BufferedReader r;
	
	public UserAcceptServer(int port, UserData data, User user)throws IOException{
		this.port = port;
		this.data = data;
		ss = new ServerSocket(port);
	}
	
	public void run(){
		try{
			startServer();
		}catch(Exception e){
			System.out.println("Something is wrong in Receiving Server");
			e.printStackTrace();
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
		switch(message[0]){
			case "2":
				WelcomeEnter();
				break;
			case "3":
				BidSucceed(message[1]);
				break;
			case "4":
				UpdateBid(message[1]);
				break;
			case "5":
				startAuctionPrint();
				PrintCItem(message[1],message[2]);
				break;
			case "6":
				endAuctionPrint();
				break;
			case "7":
				NotInAuction();
				break;
			case "8":
				BidInvalid();
				break;
			case "10":
				PrintCItem(message[1],message[2]);
				break;
		}
	}
	
	private void WelcomeEnter(){
		System.out.println("-----[Server]----- \nYou have successfully entered the lobby.\n------------------");
	}
	
	private void BidSucceed(String m){
		System.out.println("-----[Server]----- \nYou successfully bid "+m+".\n------------------");
		data.addBid(new Bid(user, Integer.parseInt(m)));
	}
	
	private void UpdateBid(String m){
		System.out.println("-----[Server]----- \n"+m+"\n------------------");
	}
	
	private void startAuctionPrint(){
		System.out.println("-----[Server]----- \nThe auction begins.\n------------------");
	}
	
	private void endAuctionPrint(){
		System.out.println("-----[Server]----- \nThe auction ends.\n------------------");
	}
	
	private void NotInAuction(){
		System.out.println("-----[Server]----- \nThe auction hasn't begin yet.\n------------------");
	}
	
	private void BidInvalid(){
		System.out.println("-----[Server]----- \nBid unsuccessful.\nEither is the bid too low or there is an invalid input\n------------------");
	}
	
	private void PrintCItem(String Name, String MinPrice){
		System.out.println("-----[Server]----- \nCurrent auction item: "+Name+"\nMinimum bid:"+MinPrice+"\n------------------");
	}

}
