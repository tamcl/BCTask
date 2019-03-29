/**
 * A class that stores information of a user
 */

class User{
	private int port;
	private String Name, ip;
	
	public User(String Name){
		this.Name = Name;
	}
	
	public User(String Name, String ip, int port){
		this.Name = Name;
		this.ip = ip;
		this.port = port;
		
	}
	
	public void UpdateInfo(String ip, int port){
		this.ip = ip;
		this.port = port;
	}
	
	public String convertToMessage(){
		String message = Name +"|"+ip+"|"+port;
		return message;
	}
	
	public String getName(){
		String[] tempName = Name.split("_");
		boolean check = false;
		String returnName = tempName[0];
		for(String N: tempName){
			if(check)returnName=returnName+" "+N;
			check=true;
		}
		return returnName;
	}
	
	public int getPort(){
		return port;
	}
	
	public String getIp(){
		return ip;
	}
}
