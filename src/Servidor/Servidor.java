package Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import Cliente.Cliente;
import Mensagem.Mensagem;
import Node.Node;

public class Servidor implements Runnable{
	public static int cont =0;
	public ServerSocket socketServidor;
	public boolean conexao = true;
	public Socket cliente;
	public int port;
	private Scanner scanner = null;
	private ObjectOutputStream saida;
	private ObjectInputStream entrada;
	private Mensagem mensagem;
	
	public Servidor(int port) {
		this.port = port;
		cont++;
	}

	@Override
	public void run() {
		try {
			socketServidor = new ServerSocket(port);
			System.out.println("servidor estabelecido! Informações abaixo");
			System.out.println("Porta:" + socketServidor.getLocalPort());
			System.out.println("Endereço ip: " + InetAddress.getLocalHost().getHostAddress());
			System.out.println("Nome do host:" + InetAddress.getLocalHost().getHostName());
			System.out.println("Aguardando conexao");
			
			while(true) {
				cliente = socketServidor.accept();
				entrada = new ObjectInputStream(cliente.getInputStream());
				System.out.println("conexao com o cliente " + cliente.getInetAddress().getHostAddress() + "/" + cliente.getInetAddress().getHostName());
				while (conexao) {
					mensagem = (Mensagem) entrada.readObject();
					if(mensagem.getDestino() == this.port) {
						Node.logMensagensRecebidas.add(mensagem);
						System.out.println("mensagem recebida:" + mensagem.getConteudo());
					}
					else if (mensagem.getDestino() == 100000){
						Node.logMensagensRecebidas.add(mensagem);
						System.out.println("mensagem recebida:" + mensagem.getConteudo());
						Cliente.mensagensPraRepassar.add(mensagem);
					}
					else {
						Cliente.mensagensPraRepassar.add(mensagem);
					}
					
				}
				cliente.close();
				socketServidor.close();
				entrada.close();
				saida.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
