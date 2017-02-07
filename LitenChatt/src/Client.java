import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		Socket socket;
		final BufferedReader in;
		final PrintWriter out;
		final Scanner sc = new Scanner(System.in);
		
		
		try {
			 // information om localhosten "ip adress & porten (skapa nytt socket obj)
			socket = new Socket("127.0.0.1",5000);
			
			//flödet för att skicka
			out = new PrintWriter(socket.getOutputStream());
			// flödet för att ta emot
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		
		// vi måste ha 2 trådar för att den sändande och mottagande skickas tillsammans
		Thread skicka = new Thread(new Runnable() {
			String msg;
			
			@Override
			public void run() {
				while(true){
					msg = sc.next();
					out.println(msg);
					out.flush();
				}
				// TODO Auto-generated method stub
				
			}
		});
		skicka.start();
		
		Thread taemot = new Thread(new Runnable() {
			String msg;
			
			@Override
			public void run() {
				while(true){
					
				try {
					msg = in.readLine();
					
				} catch (IOException e){
					e.printStackTrace();
					
				}
				System.out.println("Client :"+msg);
				
			}
	}
		});
			taemot.start();
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
