import java.io.*;
import java.util.*;
/**
 * Main class to operate ther server
 */
class ServerExecution{
	private static Scanner scan = new Scanner(System.in);
	private static ArrayList<Thread> thread = new ArrayList<Thread>();
	public static void main(String args[]) throws IOException{
		System.out.print("Enter port:");
		boolean check = true;
		int port=8000;
		while(check){
			try{
				port = scan.nextInt();
				check=false;
			}catch(Exception e){
				System.out.println("try again:");
			}
		}
		System.out.println("Port: "+ port +" confirmed.");
		//create the data storage of the server
		BidServer bd = new BidServer();
		//A thread to accept sockets from users
		BidAcceptServer BAS = new BidAcceptServer(port, bd);
		thread.add(new Thread(BAS));
		//A thread to accept commands from admins
		BidCommand BC = new BidCommand(BAS,bd);
		thread.add(new Thread(BC));
		for(Thread a: thread){
			a.start();
		}
	}
}
