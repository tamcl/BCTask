import java.util.Scanner;
import java.util.Currency;
import java.util.*;
import java.io.*;
import java.net.*;


class UserServer{
	private static String ip;
	private static int port;
	private static Scanner sc = new Scanner(System.in);
	private static User user;
	private static UserData data = new UserData();
	private static ArrayList<Thread> thread = new ArrayList<Thread>();

	
	public static void main(String args[]) throws UnknownHostException, IOException{
		enterPersonalData();
		enterServer();
		thread.add(new Thread(new UserAcceptServer(user.getPort(),data, user)));
		thread.add(new Thread(new UserBidServer(data,user,ip,port)));
		for(Thread a: thread){
			a.start();
		}
		
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
		user = new User(fullName);
	}
	
	private static void enterServer(){
		System.out.print("Enter bidserver ip address:");
		ip = sc.next();
		System.out.print("Enter bidserver port:");
		port = sc.nextInt();
		System.out.print("Enter own ip address:");
		String userip = sc.next();
		System.out.print("Enter own port:");
		int userport = sc.nextInt();
		user.UpdateInfo(userip,userport);
		try {
			new SendMessage(0,ip,port,user.convertToMessage());
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
