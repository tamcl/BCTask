import java.util.*;
import java.lang.*;
import java.io.*;
import java.net.*;
/**
 * A class stores user's data used in thread
 */
class UserData{
	//A list of successful bidding
	private ArrayList<Bid> BidList = new ArrayList<Bid>();
	//A list of items the user won 
	private ArrayList<Item> WinningItem = new ArrayList<Item>();
	
	public UserData(){
		
	}
	
	public void addBid(Bid newBid){
		BidList.add(newBid);
	}
	
	public void wonItem(Item newItem){
		WinningItem.add(newItem);
	}
	
	public Bid[] getBidList(){
		Bid[] List = BidList.toArray(new Bid[BidList.size()]);
		return List;
	}
	
	public Item[] getItemList(){
		Item[] List = WinningItem.toArray(new Item[WinningItem.size()]);
		return List;
	}
}
