import java.util.*;
import java.lang.*;

class Item{
	private int id;
	private String Name;
	private int minPrice;
	private int soldPrice;
	private User owner;
	public Item(int id, String Name){
		this.id = id;
		this.Name = Name;	
	}
	
	public Item(int id, String Name, int minPrice){
		this.id = id;
		this.Name = Name;
		this.minPrice = minPrice;
	}
	
	//methods
	
	public updateOwner(int soldPrice, User owner){
		this.soldPrice = soldPrice;
		this.owner = owner;
	}
}
