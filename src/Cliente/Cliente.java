package Cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import Mensagem.Mensagem;
import Node.Node;

public class Cliente implements Runnable{
	private Socket socket;
	private InetAddress inet;
	private String ip;
	private int porta;
	public static Queue<Mensagem> mensagensParaRepassar = new LinkedList<Mensagem>();
	public boolean conexao = true;
	private ObjectInputStream entrada;
	private ObjectOutputStream saida;
	
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public InetAddress getInet() {
		return inet;
	}
	public void setInet(InetAddress inet) {
		this.inet = inet;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPorta() {
		return porta;
	}
	public void setPorta(int porta) {
		this.porta = porta;
	}
	public ObjectInputStream getEntrada() {
		return entrada;
	}
	public void setEntrada(ObjectInputStream entrada) {
		this.entrada = entrada;
	}
	public ObjectOutputStream getSaida() {
		return saida;
	}
	public void setSaida(ObjectOutputStream saida) {
		this.saida = saida;
	}
	
	public Cliente (String ip, int porta) {
		setIp(ip);
		setPorta(porta);
	}
	
	public void run() {
		try {
			Thread.sleep(20000);
			setSocket( new Socket(ip,porta));
			setInet(socket.getInetAddress());
			System.out.println("Cliente:conexao feita com a porta" + getPorta());
			 setSaida(new ObjectOutputStream(socket.getOutputStream()));
			while(conexao) {
			
				if(mensagensParaRepassar.size() >0) {
					this.repassarMensagem(mensagensParaRepassar.remove());
				}
				Thread.sleep(2000);
			}
			getSocket().close();
			getEntrada().close();
			getSaida().close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void mandarMensagem(Mensagem mensagem) {
		try {
			getSaida().writeObject(mensagem);
			Node.logMensagensEnviadas.add(mensagem);
			getSaida().flush();
		} catch (IOException e) {
			System.out.println("nao foi possivel enviar a mensagem");
			e.printStackTrace();
		}
		
	}
	
	public void repassarMensagem(Mensagem mensagem) {
		System.out.println("repassando mensagem..");
		try {
			getSaida().writeObject(mensagem);
			getSaida().flush();
		} catch (IOException e) {
			System.out.println("nao foi possivel repassar a mensagem");
			e.printStackTrace();
		}
	}
	
}
