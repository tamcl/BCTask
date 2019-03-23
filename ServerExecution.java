import java.io.*;
import java.util.*;
class ServerExecution{
	private static Scanner scan = new Scanner(System.in);
	public static void main(String args[]) throws IOException{
		System.out.print("Enter port:");
		BidServer bd = new BidServer(scan.nextInt());
	}
}
