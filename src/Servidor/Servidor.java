package Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import Cliente.Cliente;
import Mensagem.Mensagem;
import Node.Node;

public class Servidor implements Runnable{
	private ServerSocket socketServidor;
	public boolean conexao = true;
	private Socket cliente;
	private int port;
	private ObjectOutputStream saida;
	private ObjectInputStream entrada;
	private Mensagem mensagem;
	
	public ServerSocket getSocketServidor() {
		return socketServidor;
	}

	public void setSocketServidor(ServerSocket socketServidor) {
		this.socketServidor = socketServidor;
	}

	public Socket getCliente() {
		return cliente;
	}

	public void setCliente(Socket cliente) {
		this.cliente = cliente;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public ObjectOutputStream getSaida() {
		return saida;
	}

	public void setSaida(ObjectOutputStream saida) {
		this.saida = saida;
	}

	public ObjectInputStream getEntrada() {
		return entrada;
	}

	public void setEntrada(ObjectInputStream entrada) {
		this.entrada = entrada;
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	
	
	public Servidor(int port) {
		setPort(port);
	}

	@Override
	public void run() {
		try {
			setSocketServidor(new ServerSocket(port));
			System.out.println("servidor estabelecido na porta: " + getSocketServidor().getLocalPort());
			System.out.println("Aguardando conexao");
			setCliente(socketServidor.accept());
			
			setEntrada(new ObjectInputStream(getCliente().getInputStream()));
			
			System.out.println("Servidor: conexao com o cliente estabelecida") ;
			while (conexao) {
				setMensagem((Mensagem) entrada.readObject());
				if(getMensagem().getDestino() == this.port) {
					Node.logMensagensRecebidas.add(mensagem);
					System.out.println("mensagem recebida:" + getMensagem().getConteudo());
				}
				else if (getMensagem().getDestino() == 100000){
					Node.logMensagensRecebidas.add(getMensagem());
					System.out.println("mensagem recebida:" + getMensagem().getConteudo());
					if(getMensagem().ttl <3) {
						Cliente.mensagensParaRepassar.add(getMensagem());
						getMensagem().ttl++;
					}
					
				}
				else {
					Cliente.mensagensParaRepassar.add(getMensagem());
				}
			}
			getCliente().close();
			getSocketServidor().close();
			getEntrada().close();
			getSaida().close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
