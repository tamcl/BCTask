import java.util.*;
import java.lang.*;
import java.io.*;
import java.net.*;

class BidServer {
	private ServerSocket s1;
	private Socket ss;
	private InputStreamReader in;
	private BufferedReader r;
	public BidServer() throws IOException{
		setupServer();
		System.out.println("End");
	}
	public void setupServer() throws IOException{
		s1 = new ServerSocket(8000);
		while(true){
			ss = s1.accept();
			in = new InputStreamReader (ss.getInputStream());
			r = new BufferedReader(in);
			String str = r.readLine();
			System.out.println(str);
		}
	}
}
