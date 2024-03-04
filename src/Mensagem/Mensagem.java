package Mensagem;

import java.io.Serializable;

public class Mensagem implements Serializable {
	private int remetente;
	private int destino;
	private String conteudo;
	private static final long serialVersionUID = 1L;
	public Mensagem() {}
	public Mensagem(int remetente, int destino, String conteudo) {
		setRemetente(remetente);
		setDestino(destino);
		setConteudo(conteudo);
	}
	public int getRemetente() {
		return this.remetente;
	}
	public void setRemetente(int remetente) {
		this.remetente = remetente;
	}
	public int getDestino() {
		return this.destino;
	}
	public void setDestino(int destino) {
		this.destino = destino;
	}
	public String getConteudo() {
		return this.conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	
}
