import java.util.*;
import java.lang.*;
import java.io.*;
import java.net.*;
/**
 * A class act as the storage of the server updates the list of users and items
 */
class BidServer {
	private ArrayList<User> NameList = new ArrayList<User>();
	private ArrayList<Item> ItemList = new ArrayList<Item>();
	
	private Item currentItem;
	private Bid highestBid;
	private ArrayList<Bid> currentBid;
	public BidServer(){
	}
	
	public void addName(User newUser){
		NameList.add(newUser);
	}
	
	public ArrayList<User> getNameList(){
		return NameList;
	}
	/**
	 * clear up the tempory variable
	 */
	public void setCurrentItem(Item item){
		currentItem = item;
		currentBid = new ArrayList<Bid>();
		highestBid = new Bid(item.getMinPrice());
	}
	
	public void addCurrentBid(Bid b){
		currentBid.add(b);
	}
	
	public Item getCurrentItem(){
		return currentItem;
	}
	
	public int getMinBid(){
		return currentItem.getMinPrice();
	}
	
	public int getHighestBid(){
		return highestBid.getBidPrice();
	}
	
	public Bid getHighestBidinBid(){
		return highestBid;
	}
	
	public void setHighestBid(Bid highestBid){
		this.highestBid = highestBid;
	}
	
	public ArrayList<Bid> getCurrentBidList(){
		return currentBid;
	}
	/**
	 * a method when an auction has ended
	 */
	public void wrapAuction(){
		currentItem.updateBuyer(highestBid,currentBid);
		ItemList.add(currentItem);
		try{
			new SendMessage(11, highestBid.getBider().getIp(),highestBid.getBider().getPort(),"");
		}catch(Exception e){
			
		}
	}
	
	public ArrayList<Item> getItemList(){
		return ItemList;
	}
	
	
	
}
