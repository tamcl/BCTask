import java.util.*;
import java.lang.*;
import java.io.*;
import java.net.*;

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
	
	public void setHighestBid(Bid highestBid){
		this.highestBid = highestBid;
	}
	
	public ArrayList<Bid> getCurrentBidList(){
		return currentBid;
	}
	
	public void wrapAuction(){
		currentItem.updateBuyer(highestBid,currentBid);
		ItemList.add(currentItem);
	}
	
	
}
