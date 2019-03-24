import java.util.*;
import java.lang.*;
import java.io.*;
import java.net.*;

class UserData{
	private ArrayList<Bid> BidList = new ArrayList<Bid>();
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
