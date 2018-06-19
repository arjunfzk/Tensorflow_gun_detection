import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class  Client1
{
	public Client1(String msg) {
		Socket sk;
		try {
			sk = new Socket("172.22.32.127",9898);
			BufferedReader sin=new BufferedReader(new InputStreamReader(sk.getInputStream()));
			PrintStream sout=new PrintStream(sk.getOutputStream());
			BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
			String s;
			
			while (  true )
			{
				System.out.print("Client : ");
				//s=stdin.readLine();
				s = msg;
				sout.println(s);
				s=sin.readLine();
				System.out.print("Server : "+s+"\n");
	  			if ( s.equalsIgnoreCase("BYE") )
	 			   break;
	  			break;
			}
			 sk.close();
			 sin.close();
			 sout.close();
	 		stdin.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String args[]) throws Exception
	{
		new Client1("Not found");
	}
}