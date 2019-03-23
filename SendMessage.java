import java.net.*;
import java.util.*;
import java.io.*;
import java.lang.*;

class SendMessage{
		public SendMessage(String ip, int Port, String Mes) throws IOException, UnknownHostException{
			Socket s = new Socket(ip,Port);
			PrintStream p = new PrintStream(s.getOutputStream());
			p.println(Mes);
			
		}
}
