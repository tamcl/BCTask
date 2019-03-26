import java.util.*;
import java.lang.*;

class Item{
	private String Name;
	private int minPrice;
	private Bid finalBid;
	private ArrayList<Bid> Record;
	
	public Item(String Name, int minPrice){
		this.Name = Name;
		this.minPrice = minPrice;
	}
	
	//methods
	
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
	
}
