import java.util.Scanner;

class UserBidServer implements Runnable{
	private Scanner sc = new Scanner(System.in);
	private UserData data;
	private User user;
	private String ip;
	private int port;
	public UserBidServer(UserData data, User user ,String ip, int port){
		this.data = data;
		this.user = user;
		this.ip = ip;
		this.port = port;
	}
	
	public void run(){
		startBidding();
	}
	
	public void startBidding(){
		String bidValue;
		while(true){
			bidValue = sc.next();
			try{
				Bid temp = new Bid(user,Integer.parseInt(bidValue));
				try {
					new SendMessage(1,ip,port,temp.convertToMessage());
					data.addBid(temp);
				}
				catch(Exception e){e.printStackTrace();}
			}catch(Exception e){
				System.out.println("Please place a bid.");
			}
			
		}
		
	}
}
