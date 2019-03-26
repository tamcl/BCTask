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
				}
				catch(Exception e){e.printStackTrace();}
			}catch(Exception e){
				switch(bidValue){
					case "help":
						helpPage();
						break;
					case "CItem":
						CurrentItem();
						break;
					case "SABids":
						SeeAllBids();
						break;
				}
			}
		}
	}
	
	private void helpPage(){
		System.out.println("");
		System.out.println("CItem \t see current Item");
		System.out.println("SABids \t see all bids");
	}
	
	private void CurrentItem(){
		try{
			new SendMessage(9,ip,port,user.convertToMessage());
		}catch(Exception e){
			
		}
	}
	
	private void SeeAllBids(){
		System.out.println("-----[Bidding record]-----");
		int count = 1;
		for(Bid a:data.getBidList()){
			System.out.println(count + ". Bid: "+a.getBidPrice()+" on "+a.getItemName());
		}
		System.out.println("----------");
	}
}
