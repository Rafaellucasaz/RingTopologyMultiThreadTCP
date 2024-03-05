package Mensagem;

import java.io.Serializable;


public class Mensagem implements Serializable {
	public int ttl =0;
	private int remetente;
	private int destino;
	private String conteudo;
	private static final long serialVersionUID = 1L;
	public Mensagem() {}
	public int getRemetente() {
		return this.remetente;
	}
	public void setRemetente(int remetente) {
		this.remetente = remetente;
	}
	public int getDestino() {
		return this.destino;
	}
	public void setDestino(int destino)throws Exception {
		if(destino!= 5465 & destino!= 5466 && destino !=5467 && destino !=5468 && destino != 100000 ) {
			throw new Exception("porta invalida");
		}
		else {
			this.destino = destino;
		}
		
	}
	public String getConteudo() {
		return this.conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	
}
