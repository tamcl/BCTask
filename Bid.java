/**
 * A class that stores the data of a bid
 */

class Bid{
	private User bider;
	private int bidPrice;
	private String itemName;
	
	public Bid(User bider, int bidPrice){
		this.bider = bider;
		this.bidPrice = bidPrice;
	}
	
	public Bid(User bider, int bidPrice, String itemName){
		this.bider = bider;
		this.bidPrice = bidPrice;
		this.itemName = itemName;
	}
	
	public Bid(int bidPrice){
		this.bidPrice = bidPrice;
	}
	
	public String convertToMessage(){
		String message = bider.convertToMessage()+"|"+Integer.toString(bidPrice);
		return message;
	}
	
	public User getBider(){
		return bider;
	}
	
	public int getBidPrice(){
		return bidPrice;
	}
	
	public String getItemName(){
		return itemName;
	}
 
}
