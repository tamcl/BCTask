import java.net.*;
import java.util.*;
import java.io.*;
import java.lang.*;


class BidCommand implements Runnable{
	private BidAcceptServer BAS;
	private BidServer BS;
	private Scanner scan = new Scanner(System.in);
	public BidCommand(BidAcceptServer BAS, BidServer BS){
		this.BAS = BAS;
		this.BS = BS;
	}
	
	public void run(){
		startCommandRecieve();
	}
	
	private void startCommandRecieve(){
		String command;
		while(true){
			command = scan.next();
			try{
				Command(command);
			}catch(Exception e){
				System.out.println("Something is wrong");
			}
			
		}
	}
	
	private void Command(String command)throws IOException{
		switch(command){
			case "help":
				helpPage();
				break;
			case "startAuction":
				setItem();
				startAuction();
				break;
			case "endAuction":
				endAuction();
				break;
			case "LBOCI":
				ListBidsOfCurrentItem();
				break;
			
		}
		
	}
	
	private void helpPage(){
		System.out.println("");
		System.out.println("startAuction \t set the Item then start auction");
		System.out.println("endAuction \t end auction");
		System.out.println("LBOCI \t List Bids Of Current Item");
		System.out.println("");
	}
	
	private void setItem(){
		boolean check = true;
		while(check){
			try{
				check = false;
				System.out.print("Name of Item: ");
				String name = scan.next();
				System.out.print("Minimum bid of the item(if none type 0): ");
				int MP = scan.nextInt();
				BS.setCurrentItem(new Item(name, MP));
			}catch(Exception e){
				check = true;
			}
		}
	}
	
	private void startAuction()throws IOException{
		BAS.startAuction();
		for(User user:BS.getNameList()){
			new SendMessage(5,user.getIp(),user.getPort(),BS.getCurrentItem().getName()+"|"+Integer.toString(BS.getCurrentItem().getMinPrice()));
		}
	}
	
	private void endAuction()throws IOException{
		BAS.endAuction();
		BS.wrapAuction();
		for(User user:BS.getNameList()){
			new SendMessage(6,user.getIp(),user.getPort(),"");
		}
	}
	
	private void ListBidsOfCurrentItem(){
		System.out.println("-----[Bidding List]-----");
		int count = 1;
		for(Bid a:BS.getCurrentBidList()){
			System.out.println(count+". "+a.getBider().getName() + " bids: " + a.getBidPrice());
			count++;
		}
		System.out.println("----------");
	}
}
