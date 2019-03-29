import java.util.*;
import java.lang.*;
/**
 * A class stores information about Item
 * 
 */

class Item{
	private String Name;
	private int minPrice;
	private Bid finalBid;
	private ArrayList<Bid> Record;
	
	public Item(String Name, int minPrice){
		this.Name = Name;
		this.minPrice = minPrice;
	}
	
	/**
	 * a method that updates when the auction ends
	 */
	public void updateBuyer(Bid finalBid, ArrayList<Bid> Record){
		this.finalBid = finalBid;
		this.Record = Record;
	}
	
	public int getMinPrice(){
		return minPrice;
	}
	
	public String getName(){
		return Name;
	}
	
	public Bid getWinner(){
		return finalBid;
	}
	
	public ArrayList<Bid> getRecord(){
		return Record;
	}
	
}
