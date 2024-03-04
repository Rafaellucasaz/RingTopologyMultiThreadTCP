package Cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import Mensagem.Mensagem;
import Node.Node;

public class Cliente implements Runnable{
	public Socket socket;
	public InetAddress inet;
	public String ip;
	public int porta;
	public Thread t;
	private boolean conexao = true;
	private ObjectInputStream entrada;
	private ObjectOutputStream saida;
	public Cliente (String ip, int porta) {
		this.ip = ip;
		this.porta = porta;
	}
	public void run() {
		try {
			Thread.sleep(20000);
			socket = new Socket(ip,porta);
			inet = socket.getInetAddress();
			System.out.println("conexao feita com a porta" + porta);
			saida = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mandarMensagem(Mensagem mensagem) {
		try {
			saida.writeObject(mensagem);
			Node.logMensagensEnviadas.add(mensagem);
			saida.flush();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
}
