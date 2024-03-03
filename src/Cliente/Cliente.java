package Cliente;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import Node.Node;

public class Cliente implements Runnable{
	public Socket socket;
	public InetAddress inet;
	public String ip;
	public int porta;
	public Thread t;
	public Cliente (String ip, int porta) {
		this.ip = ip;
		this.porta = porta;
		this.run();
	}
	public void run() {
		try {
			Thread.sleep(20000);
			socket = new Socket(ip,porta);
			inet = socket.getInetAddress();
			System.out.println("conexao feita!");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mandarMensagem() {
		ImplCliente cliente = new ImplCliente(socket);
		t = new Thread(cliente);
		t.start();
	}
	
	
}
