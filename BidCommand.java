import java.net.*;
import java.util.*;
import java.io.*;
import java.lang.*;
/**
 * A thread that accept command from the admins
 */

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
		//a while true to accpet scanner
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
			case "CWBid":
				CWBid();
				break;
			case "SAItem":
				SAItem();
				break;
			case "FItemInfo":
				FItemInfo();
				break;
			case "SAUser":
				SAUser();
				break;
			case "FUserBid":
				FUserBid();
				break;
		}
		
	}
	
	private void helpPage(){
		System.out.println("");
		System.out.println("startAuction \t set the Item then start auction");
		System.out.println("endAuction \t end auction");
		System.out.println("LBOCI \t List Bids Of Current Item");
		System.out.println("CWBid \t print the current winning bid");
		System.out.println("SAItem \t show all item");
		System.out.println("FItemInfo \t Find Item information");
		System.out.println("SAUser \t show all username");
		System.out.println("FUserBid \t Find certain user's bidding history");
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
	/**
	 * method to start an auction
	 */
	private void startAuction()throws IOException{
		BAS.startAuction();
		for(User user:BS.getNameList()){
			new SendMessage(5,user.getIp(),user.getPort(),BS.getCurrentItem().getName()+"|"+Integer.toString(BS.getCurrentItem().getMinPrice()));
		}
	}
	/**
	 * method to end an auction
	 */
	private void endAuction()throws IOException{
		BAS.endAuction();
		BS.wrapAuction();
		for(User user:BS.getNameList()){
			new SendMessage(6,user.getIp(),user.getPort(),"");
		}
	}
	/**
	 * method to show the list of bids of the current item or the most recent item
	 */
	private void ListBidsOfCurrentItem(){
		System.out.println("-----[Bidding List]-----");
		int count = 1;
		for(Bid a:BS.getCurrentBidList()){
			System.out.println(count+". "+a.getBider().getName() + " bids: " + a.getBidPrice());
			count++;
		}
		System.out.println("----------");
	}
	/**
	 * method to show the current winning user and it's bid
	 */
	private void CWBid(){
		Bid tempBid = BS.getHighestBidinBid();
		System.out.println("-----[Current winning bid]-----");
		System.out.println(tempBid.getBider().getName() + " has the highest bid.");
		System.out.println("Bid: "+tempBid.getBidPrice());
		System.out.println("-------------------------------");
	}
	/**
	 * method to show all the items that was in the auction
	 */
	private void SAItem(){
		System.out.println("-----[Item List]-----");
		for(Item a: BS.getItemList()){
			System.out.println(a.getName());
		}
		System.out.println("----------");
	}
	/**
	 * method that will return the item information when item name was entered
	 */
	private void FItemInfo(){
		System.out.print("Enter item name: ");
		String TargetName = scan.next();
		boolean found = false;
		Item temp = new Item("Sample", 0);
		for(Item a: BS.getItemList()){
			if(a.getName().equals(TargetName)){
				found = true;
				temp = a;
				break;
			}
		}
		if(found == false){
			System.out.println("Error: item not found.");
		}else{
			try{
				System.out.println("-----[Final bid]-----");
				System.out.println("Winner: "+temp.getWinner().getBider().getName());
				System.out.println("Bid: "+temp.getWinner().getBidPrice());
				System.out.println("-----[Bid Record]-----");
				int count = 1;
				for(Bid a: temp.getRecord()){
					System.out.println(count+". "+a.getBider().getName()+" bids "+a.getBidPrice());
					count++;
				}
				System.out.println("----------");
			}catch(Exception e){
				
			}
		}
	}
	/**
	 * method to show all the users entered the server
	 */
	private void SAUser(){
		System.out.println("-----[User List]-----");
		for(User a: BS.getNameList()){
			System.out.println(a.getName());
		}
		System.out.println("----------");
	}
	/**
	 * method to show all the items that the user has bid when the user name was entered
	 */
	private void FUserBid(){
		System.out.print("Enter User Name: ");
		String TargetName = scan.next();
		boolean found = false;
		User temp = new User("Sample");
		for(User a: BS.getNameList()){
			if(a.getName().equals(TargetName)){
				found = true;
				temp = a;
				break;
			}
		}
		
		if(found){
			System.out.println("-----[Item user has bid]-----");
			for(Item a: BS.getItemList()){
				for(Bid b: a.getRecord()){
					if(b.getBider().getName().equals(TargetName)){
						System.out.println(a.getName());
						break;
					}
				}
			}
		}
	}
}
