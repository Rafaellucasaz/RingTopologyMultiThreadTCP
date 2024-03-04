package Node;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import Cliente.Cliente;
import Cliente.ImplCliente;
import Mensagem.Mensagem;
import Servidor.ImplServidor;
import Servidor.Servidor;

public class Node {
	public static ArrayList<Mensagem> logMensagensRecebidas = new ArrayList<Mensagem>();
	public static ArrayList<Mensagem> logMensagensEnviadas = new ArrayList<Mensagem>();
	Scanner scanner = new Scanner(System.in);
	Servidor servidorInstance;
	Thread servidor;
	Cliente cliente1;
	Thread conexao1;
	int proxPorta;
	int portaServidor;
	String ip;
	public Node(int portaServer,int proxPorta,String ip) {
		this.portaServidor = portaServer;
		this.proxPorta = proxPorta;
		this.servidorInstance = new Servidor(portaServer);
		this.ip = ip;
		this.run();
	}
	private void run() {
		this.servidor = new Thread(servidorInstance);
		servidor.start();
		this.cliente1 = new Cliente(ip,proxPorta);
		this.conexao1 = new Thread(cliente1);
		conexao1.start();
		System.out.println("1-mandar mensagem");
		System.out.println("2-mostrar log de mensagens recebidas");
		System.out.println("3-mostrar log de mensagens enviadas");
		System.out.println("4-fechar conexao");
		switch (scanner.nextInt()) {
		
		case 1: {
			Mensagem mensagem = new Mensagem();
			System.out.println("digite a porta de destino:(5465,5466,5467,5468)");
			mensagem.setDestino(scanner.nextInt());
			scanner.nextLine();
			System.out.println("digite a mensagem:");
			mensagem.setConteudo(scanner.nextLine());
			mensagem.setRemetente(cliente1.socket.getLocalPort());
			cliente1.mandarMensagem(mensagem);
		}
		default:
			
		}
		
		
	}

}
