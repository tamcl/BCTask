import java.util.Scanner;
import java.util.Currency;
import java.util.*;
import java.io.*;
import java.net.*;


class UserServer{
	private static String ip;
	private static int port;
	private static Scanner sc = new Scanner(System.in);

	
	public static void main(String args[]) throws UnknownHostException, IOException{
		enterPersonalData();
		enterServer();
		goBid();
		
	}
	
	private static void enterPersonalData(){
		System.out.print("Enter Name:");
		String[] Name = sc.nextLine().split(" ");
		String fullName=Name[0];
		int count = 0;
		for(String temp:Name){
			if(count!=0)fullName=fullName+"_"+temp;
			count++;
		}
		System.out.println(fullName);
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
			temp = sc.nextLine();
			try {new SendMessage(ip,port,temp);}
			catch(Exception e){e.printStackTrace();}
		}
	}
}
