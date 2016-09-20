import java.net.*;
import java.io.*;

public class ProductorSocket {
	
	final static String HOST = "localhost";
	final static int PUERTO=5001;
	
	static Socket sc;
	
	static DataOutputStream mensaje;
	
	DataInputStream entrada;
	
	public static void main(String args[]) throws Exception
	{
		try{
			sc = new Socket(HOST,PUERTO);
			
			mensaje = new DataOutputStream(sc.getOutputStream());
			
			mensaje.writeUTF("Productor");
			
			sc.close();
		}catch(Exception e){
			System.out.println("Error"+ e.getMessage());
		}
	
	}

}