import java.io.*;
import java.util.*;
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
		BidServer bd = new BidServer();
		thread.add(new Thread(new BidAcceptServer(port, bd)));
		for(Thread a: thread){
			a.start();
		}
	}
}
