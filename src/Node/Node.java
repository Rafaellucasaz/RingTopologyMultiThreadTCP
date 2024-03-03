package Node;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import Cliente.Cliente;
import Cliente.ImplCliente;
import Servidor.ImplServidor;
import Servidor.Servidor;

public class Node {
	public static ArrayList<String> logMensagensRecebidas = new ArrayList<String>();
	public static ArrayList<String> logMensagensEnviadas = new ArrayList<String>();
	Scanner scanner = new Scanner(System.in);
	Servidor servidorInstance;
	Thread servidor;
	Cliente cliente1;
	Thread conexao1;
	int portaEsquerda;
	int portaDireita;
	int portaServidor;
	public Node(int portaServer,int portaEsquerda,int portaDireita,String ip) {
		this.portaEsquerda = portaEsquerda;
		this.portaDireita = portaDireita;
		this.portaServidor = portaServer;
		this.servidorInstance = new Servidor(portaServer);
		this.servidor = new Thread(servidorInstance);
		this.cliente1 = new Cliente(ip,portaDireita);
		this.conexao1 = new Thread(cliente1);
		this.run();
	}
	private void run() {
		servidor.start();
		conexao1.start();
		System.out.println("1-mandar mensagem");
		System.out.println("2-mostrar log de mensagens recebidas");
		System.out.println("3-mostrar log de mensagens enviadas");
		System.out.println("4-fechar conexao");
		switch (scanner.nextInt()) {
		case 1: {
			System.out.println("qual port ? (5465/5466/5467/5468)");
			mandarMensagem(scanner.nextInt());
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + scanner);
		}
		
	}
	public void mandarMensagem(int port) {
		cliente1.mandarMensagem();
	}
	
	

}
