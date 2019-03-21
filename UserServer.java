import java.util.Scanner;
import java.io.*;
import java.net.*;


class UserServer{
	public static void main(String args[]) throws UnknownHostException, IOException{
		Scanner sc = new Scanner(System.in);
		Socket s = new Socket("127.0.0.1",8000);
		Scanner sc2 = new Scanner(s.getInputStream());
		PrintStream p = new PrintStream(s.getOutputStream());
		String number = "5";
		p.println(number);
		
	}
}
