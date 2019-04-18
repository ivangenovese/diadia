package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Memorizza gli oggetti di un giocatore
 * 
 * @author IVAN_KOVALOV
 *
 */
public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	public final static int DEFAULT_NUMERO_MAX_ATTREZZI = 10;
	private Attrezzo[] attrezzi; //Array di attrezzi
	private int numeroAttrezzi; //contatore di attrezzi
	private int pesoMax; // peso massimo della borsa

	/**
	 * Costruttore senza paramentri
	 *////////////////////////////
	
	
	/**
	 * Chiama costruttore Passa Peso massimo della borsa di default
	 */////////////////////////////////////////////////////////////
	 
	
	public Borsa() {

		this(DEFAULT_PESO_MAX_BORSA);
	}

	/**
	 * Peso massimo di una borsa
	 * 
	 * @param pesoMax
	 *///////////////
	
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax; 
		this.attrezzi = new Attrezzo[DEFAULT_NUMERO_MAX_ATTREZZI]; // speriamo che bastino...
		this.numeroAttrezzi = 0;
	}

	/**
	 * Aggiungere attrezzi nella borsa del giocatore
	 * 
	 * @param attrezzo
	 * @return ritorna true se attrezzo è stato aggiunto, altrimenti false
	 */////////////////////////////////////////////////////////////////////
	
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (!this.pesoSufficientePerAggiungereAttrezzo(attrezzo))
			return false;
		if (this.numeroAttrezzi == DEFAULT_NUMERO_MAX_ATTREZZI)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}
	
	/**
	 * Gettere peso massimo
	 * @return peso massimo
	 *////////
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	/**
	 * Restitiusce il riferimento all'oggetto Attrezzo
	 * @param nomeAttrezzo stringa nome dell'attrezzo
	 * @return se presente restituisce il riferimento all'oggetto Attrezzo, altrimenti null
	 */////////////////////////////////////////////////////////////////////////////////////
	
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i = 0; i < this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];
		return a;
	}
	
	/**
	 * Getter peso della borsa
	 * @return intero
	 *///////////////
	
	
	
	public int getPeso() {
		int peso = 0;
		for (int i = 0; i < this.numeroAttrezzi; i++)
			if(this.attrezzi[i] != null) {
				peso += this.attrezzi[i].getPeso();
			}
			
		return peso;
	}
	
	/**
	 * Controllo se la borsa è vuota
	 * @return booleano
	 */////////////////
	
	
	
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	
	/**
	 * Controllo se attrezzo è presente nella borsa
	 * @param nomeAttrezzo nome stringa dell'attrezzo
	 * @return true se la borsa ha questo attrezzo, altrimenti false
	 *//////////////////////////////////////////////////////////////
	
	
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}
	
	/**
	 * Rimuovere un attrezzo dalla borsa
	 * @param nomeAttrezzo nome stringa dell'attrezzo
	 * @return restituisce true se l'attrezzo è stato rimosso, altrimenti false
	 *//////////////////////////////////////////////////////////////////////////
	
	
	
	public boolean removeAttrezzo(String nomeAttrezzo) {
		
		int i;
		
		for(i = 0; i < this.numeroAttrezzi; i++) {
			if(this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
				this.attrezzi[i] = this.attrezzi[this.numeroAttrezzi - 1];
				this.attrezzi[this.numeroAttrezzi - 1] = null;
				this.decrementaNumeroAttrezzi();
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Restituisce la stringa con il contenuto della borsa
	 *////////////////////////////////////////////////////
	
	
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
			for (int i = 0; i < this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString() + " ");
		} else
			s.append("Borsa vuota");
		s.append("\nPeso della borsa: " + this.getPeso() + "kg");
		return s.toString();
	}
	
	/**
	 * Decrementa il numero di attrezzi nella borsa
	 */////////////////////////////////////////////
	
	
	public void decrementaNumeroAttrezzi() {
		this.numeroAttrezzi--;
	}
	
	/**
	 * Controlla se la borsa e piena
	 * @return
	 *///////////////////////////////
	
	
	public boolean isPieno() {
		return ((this.getNumeroAttrezzi() == DEFAULT_NUMERO_MAX_ATTREZZI) || (this.getPeso() == DEFAULT_PESO_MAX_BORSA));
	}
	
	/**
	 * Il peso rimasto della borsa è sufficiente per aggiungere attrezzo
	 * @param attrezzo
	 * @return ritorna true se peso è sufficiente, altrimenti false
	 *//////////////////////////////////////////////////////////////
	
	
	public boolean pesoSufficientePerAggiungereAttrezzo(Attrezzo attrezzo) {
		return ((this.getPeso() + attrezzo.getPeso()) <= this.getPesoMax());
	}
	
	/**
	 * Controllo se e possibile aggiungere attrezzo nella borsa 
	 * @param attrezzo
	 * @return
	 *////////
	
	
	public boolean possibileAggiungereAttrezzo(Attrezzo attrezzo) {
		
		if(attrezzo == null) {
			return false;
		}
		
		if(!this.isPieno() && this.pesoSufficientePerAggiungereAttrezzo(attrezzo)) {
			return true;
		} 
		else {
			return false;
		}
	}

	/**
	 * Getter numero attrezzi nella borsa
	 * @return numero attrezzi
	 *////////
	
	
	public int getNumeroAttrezzi() {
		return numeroAttrezzi;
	}

	/**
	 * Setter numero attrezzi nella borsa
	 * @param numeroAttrezzi
	 *//////////////////////
	
	public void setNumeroAttrezzi(int numeroAttrezzi) {
		this.numeroAttrezzi = numeroAttrezzi;
	}
	
	
}