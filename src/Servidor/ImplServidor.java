package Servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import Node.Node;

public class ImplServidor implements Runnable{
	public Socket socketCliente;
	private boolean conexao =true;
	private Scanner scanner = null;
	private PrintStream resposta;
	
	public ImplServidor(Socket cliente ) {
		socketCliente = cliente;
		try {
			resposta = new PrintStream(socketCliente.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void run() {
		String mensagem;
		System.out.println("conexao com o cliente " + socketCliente.getInetAddress().getHostAddress() + "/" + socketCliente.getInetAddress().getHostName());
		try {
			scanner = new Scanner(socketCliente.getInputStream());
			while(conexao) {
				mensagem = scanner.nextLine();
				System.out.println("mensagem recebida:" + mensagem);
				conexao =false;
			}
			scanner.close();
			System.out.println("fim do cliente" + socketCliente.getInetAddress().getHostAddress());
			socketCliente.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}
}
