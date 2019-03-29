import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 * A thread that accepts sockets from the users to do commands
 */
class BidAcceptServer implements Runnable{
	private ServerSocket ss;
	private static int port;
	private BidServer data;
	
	private Socket s;
	private InputStreamReader in;
	private BufferedReader r;
	
	private boolean InAuction = false;
	
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
		switch(message[0]){
			case "0":
				addNameList(message);
				break;
			case "1":
				if(InAuction){
					bidShout(message);
				}else{
					NotDuringAuction(message);
				}
				break;
			case "9":
				sendCurrentItem(message);
		}
	}
	/**
	 * method to add Name List when new user entered
	 */
	private void addNameList(String[] message){
		User newUser = new User(message[1]);
		newUser.UpdateInfo(message[2],Integer.parseInt(message[3]));
		data.addName(newUser);
		try{
			//to tell if the user has successfully entered the server
			new SendMessage(2, newUser.getIp(), newUser.getPort(), "");
			if(InAuction){
				new SendMessage(5,newUser.getIp(),newUser.getPort(),"");
			}
		}catch(Exception e){
			System.out.println("message sent error");
		}
	}
	/**
	 * When a bid is placed this method is triggered
	 */
	private void bidShout(String[] message){
		int Value = -1;
		boolean number = true;
		try{Value = Integer.parseInt(message[4]);}catch(Exception e){number=false;}
		boolean success = false;
		if(Value>data.getMinBid()&&Value>data.getHighestBid()){
			success=true;
		}
		//if bid is a number and if the bid is higher than the last bid
		//then update all the current bid and update winning bidder and record of bids
		if(success==true&&number==true){
			try{
				Bid newBid = new Bid(new User(message[1],message[2],Integer.parseInt(message[3])),Value);
				data.addCurrentBid(newBid);
				data.setHighestBid(newBid);
				//to tell when bid success
				new SendMessage(3, message[2], Integer.parseInt(message[3]), message[4]+"|"+data.getCurrentItem().getName());
			}catch(Exception e){
				System.out.println("message sent error");
			}
			for(User a:data.getNameList()){
				try{
					//to tell the current highest bid
					new SendMessage(4, a.getIp(), a.getPort(), "Current Highest Bid: "+message[4]);
				}catch(Exception e){
				System.out.println("something wrong");
				}
			}
		}else{
			try{
				//to tell that the bid is invalid
				new SendMessage(8, message[2], Integer.parseInt(message[3]),"");
			}catch(Exception e){
				System.out.println("message sent error");
			}
		}
			
	}
	/**
	 * method when bid is places but not during auction
	 */
	private void NotDuringAuction(String[] message){
		try{
			//to tell user that is not during auction
			new SendMessage(7, message[2],Integer.parseInt(message[3]),"");
		}catch(Exception e){
			System.out.println("cannot send 'Not During Action' message to client.");
		}
		
	}
	
	public void startAuction(){
		InAuction = true;
	}
	
	public void endAuction(){
		InAuction = false;
	}
	/**
	 * method to send current item information to users
	 */
	public void sendCurrentItem(String[] message){
		try{
			new SendMessage(10,message[2],Integer.parseInt(message[3]),data.getCurrentItem().getName()+"|"+Integer.toString(data.getCurrentItem().getMinPrice()));
		}catch(Exception e){}
	}
}
