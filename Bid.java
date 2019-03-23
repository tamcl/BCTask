class Bid{
	private User bider;
	private int bidPrice;
	private String itemName;
	
	public Bid(User bider, int bidPrice/*, String itemName*/){
		this.bider = bider;
		this.bidPrice = bidPrice;
		/*this.itemName = itemName;*/
	}
	
	public String convertToMessage(){
		String message = bider.convertToMessage()+"|"+Integer.toString(bidPrice);
		return message;
	}
}
