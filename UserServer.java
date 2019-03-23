import java.util.Scanner;
import java.util.Currency;
import java.io.*;
import java.net.*;


class UserServer{
	private static String ip;
	private static int port;
	private static Scanner sc = new Scanner(System.in);

	
	public static void main(String args[]) throws UnknownHostException, IOException{
		enterServer();
		goBid();
		
	}
	
	private static void enterServer(){
		System.out.print("Enter server ip address:");
		ip = sc.next();
		System.out.print("Enter port:");
		port = sc.nextInt();
	}
	
	private static void goBid(){
		String temp;
		while(true){
			temp = sc.next();
			try {new SendMessage(ip,port,temp);}
			catch(Exception e){e.printStackTrace();}
		}
	}
}
