import java.net.*;
import java.util.*;
import java.io.*;
import java.lang.*;
/**
 * A class to send message to a certain address/ port with a specific code or message
 */
class SendMessage{
		public SendMessage(int code, String ip, int Port, String Mes) throws IOException, UnknownHostException{ //code 0 enter server, code 1 bid item
			Socket s = new Socket(ip,Port);
			PrintStream p = new PrintStream(s.getOutputStream());
			p.println(code+"|"+Mes);
			
		}
}
