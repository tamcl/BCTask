import java.net.*;
import java.util.*;
import java.io.*;
import java.lang.*;

class SendMessage{
		public SendMessage(int code, String ip, int Port, String Mes) throws IOException, UnknownHostException{ //code 0 enter server, code 1 bid item
			Socket s = new Socket(ip,Port);
			PrintStream p = new PrintStream(s.getOutputStream());
			p.println(code+"|"+Mes);
			
		}
}
