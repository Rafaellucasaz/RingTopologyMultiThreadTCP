package Node;


import java.util.ArrayList;
import java.util.Scanner;
import Cliente.Cliente;
import Mensagem.Mensagem;
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
	int aux =0;
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
		while(aux!=4) {
			System.out.println("1-mandar mensagem");
			System.out.println("2-mostrar log de mensagens recebidas");
			System.out.println("3-mostrar log de mensagens enviadas");
			System.out.println("4-fechar conexao");
			aux = scanner.nextInt();
			scanner.nextLine();
			try {
				switch (aux) {
				
				case 1: 
					Mensagem mensagem = new Mensagem();
					System.out.println("Broadcast ou Unicast ?:");
					String tipoMensagem = scanner.nextLine();
					if(tipoMensagem.equalsIgnoreCase("broadcast")) {
						mensagem.setDestino(100000);
					}
					else if(tipoMensagem.equalsIgnoreCase("unicast")) {
						System.out.println("digite a porta de destino:(5465,5466,5467,5468)");
						mensagem.setDestino(scanner.nextInt());
						scanner.nextLine();
					}
					else {
						System.out.println("entrada invalida");
						break;
					}
					System.out.println("digite a mensagem:");
					mensagem.setConteudo(scanner.nextLine());
					mensagem.setRemetente(portaServidor);
					cliente1.mandarMensagem(mensagem);
					break;
				case 2:
					for(int i =0; i < logMensagensRecebidas.size();i++) {
						System.out.println("mensagem " + (i+1) + " : " + logMensagensRecebidas.get(i).getConteudo() );
					}
					break;
				case 3:
					for(int i = 0; i < logMensagensEnviadas.size();i++) {
						System.out.println("mensagem " + (i+1) + " : " + logMensagensEnviadas.get(i).getConteudo());
					}
					break;
				case 4:
					servidorInstance.conexao = false;
					cliente1.conexao = false;
					
					break;
				default:
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	

}
