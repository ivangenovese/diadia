package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Giocatore {
	static final private int CFU_INIZIALI = 20;
	private String nome;
	private int cfu;
	private Borsa borsa;
	
	/**
	 * Construtture della classe Giocatore
	 * @param name nome del giocatore
	 */
	public Giocatore(String name) {
		this.nome = name;
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCfu() {
		return cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	public Borsa getBorsa() {
		return borsa;
	}

	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}

	public static int getCfuIniziali() {
		return CFU_INIZIALI;
	}
	
	/**
	 * Decremento CFU
	 */
	public void decrementaCfu() {
		if(this.cfu > 0) {
			this.cfu--;
		}
	}
	
	/**
	 * Aggiungere attrezzo nella borsa
	 * @param attrezzo
	 * @return
	 */
	public boolean prendereAttrezzo(Attrezzo attrezzo) {
		
		if(attrezzo == null) {
			return false;
		}
		
		if(this.getBorsa().possibileAggiungereAttrezzo(attrezzo)) {
			return this.getBorsa().addAttrezzo(attrezzo);
		}
		
		return false;
	}
	
}
