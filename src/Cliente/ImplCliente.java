package Cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import Node.Node;

public class ImplCliente implements Runnable {
	private Socket cliente;
	private boolean conexao = true;
	private Scanner entrada;
	private PrintStream saida;
	
	public ImplCliente(Socket cliente) {
		this.cliente = cliente;
	}
	
	public void run() {
		try {
			Scanner scanner = new Scanner(System.in);
			entrada = new Scanner(cliente.getInputStream());
			saida = new PrintStream(cliente.getOutputStream());
			String mensagem;
			
			while(conexao) {
				mensagem = "teste";
				saida.println(mensagem);
			}
			saida.close();
			scanner.close();
			cliente.close();
			System.out.println("conexao finalizada");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
