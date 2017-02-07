
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.RunnableScheduledFuture;




public class Server {

	public static void main(String[] args) {
		ServerSocket socketserver;
		Socket sockettillserver;
		final BufferedReader in;
		final PrintWriter out;
		final Scanner sc = new Scanner(System.in);
		
		// skapa serversocket med port nummer 5000
		try {
			socketserver = new ServerSocket(5000);
			// ansluta sig till ett utag och acceptera det
			sockettillserver = socketserver.accept();
			//flödet för att skicka
			out = new PrintWriter(sockettillserver.getOutputStream());
			// flödet för att ta emot
			in = new BufferedReader(new InputStreamReader(sockettillserver.getInputStream()));
		
		
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
				System.out.println("Server :"+msg);
				
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
