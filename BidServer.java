import java.util.*;
import java.lang.*;
import java.io.*;
import java.net.*;

class BidServer {
	private ArrayList<User> NameList = new ArrayList<User>();
	public BidServer(){
	}
	
	public void addName(User newUser){
		NameList.add(newUser);
	}
	
	public ArrayList<User> getNameList(){
		return NameList;
	}
	
	
}
