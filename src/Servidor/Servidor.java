package Servidor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor implements Runnable{
	public static int cont =0;
	public ServerSocket socketServidor;
	public Socket cliente;
	public int port;
	
	public Servidor(int port) {
		this.port = port;
		cont++;
	}

	@Override
	public void run() {
		try {
			socketServidor = new ServerSocket(port);
			System.out.println("Porta:" + socketServidor.getLocalPort());
			System.out.println("Endereço ip: " + InetAddress.getLocalHost().getHostAddress());
			System.out.println("Nome do host:" + InetAddress.getLocalHost().getHostName());
			System.out.println("Aguardando novo cliente...");
			
			while(true) {
				cliente = socketServidor.accept();
				ImplServidor teste = new ImplServidor(cliente);
				
				Thread t = new Thread(teste);
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
