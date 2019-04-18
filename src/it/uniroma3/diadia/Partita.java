package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	private boolean finita; //Flag se la partita e terminata
	private Labirinto labirinto; //oggetto Labirinto
	private Giocatore giocatore; // oggetto Giocatore
	/**
	 * Construttore della classe partita
	 * Inizializza le stanze e labirinto
	 * Inizializza: CFU 
	 * Imposta: partita vinta = false
	 *///////////////////////////////
	public Partita() {
		creaStanze(); //Inizializzazione delle stanza e attrezzi
		this.finita = false;
		this.giocatore = new Giocatore("Ivan");
	}

	/**
	 * Crea tutte le stanze e le porte di collegamento
	 *////////////////////////////////////////////////
	
	private void creaStanze() {

		/* crea gli attrezzi */
		Attrezzo lanterna = new Attrezzo("lanterna", 3);
		Attrezzo osso = new Attrezzo("osso", 1);

		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");

		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

		/* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		// Generazione delle stanza di inizio e fine di gioco
		this.labirinto = new Labirinto(atrio, biblioteca);

	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * 
	 * @return vero se partita vinta
	 *//////////////////////////////
	
	
	public boolean vinta() {
		return this.getLabirinto().stanzeUguali();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * 
	 * @return vero se partita finita
	 *///////////////////////////////
	
	public boolean isFinita() {
		return this.finita || this.vinta() || (this.getGiocatore().getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *///////////////////////////////
	
	public void setFinita() {
		this.finita = true;
	}

	/**
	 * Getter oggetto Labirinto
	 * @return
	 */////////////////////////
	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	/**
	 * Getter oggetto Giocatore
	 * @return
	 *////////////////////////
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	
	/**
	 * Setter oggetto Giocatore
	 * @param giocatore
	 */
	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}
	
	/**
	 * Riferimento ad attrezzo della stanza corrente
	 * @param nomeAttrezzo
	 * @return
	 *////////
	
	public boolean getAttrezzoStanzaCorrente(String nomeAttrezzo) {
		Attrezzo a = this.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		
		if(a == null) {
			return false;
		}
		
		return false;
	}
	
	/**
	 * Prendere attrezzo dalla stanza
	 *///////////////////////////////
	
	
	public void prendiAttrezzo(String nomeAttrezzo) {
		Attrezzo a = this.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		boolean flag = this.getGiocatore().prendereAttrezzo(a);
		if(flag) {
			this.getLabirinto().getStanzaCorrente().removeAttrezzo(a);
			System.out.println("Attrezzo e stato aggiunto nello zaino");
		} else {
			System.out.println("Non e stato possibile aggiungere attrezzo nello zaino");
		}
	}
	
	/**
	 * Posare attrezzo dallo zaino
	 * @param nomeAttrezzo
	 *////////////////////
	
	
	public void posaAttrezzo(String nomeAttrezzo) {
		Attrezzo a = this.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		boolean flag = this.getLabirinto().getStanzaCorrente().addAttrezzo(a);
		if(flag) {
			this.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
			System.out.println("Attrezzo e stato posato nella stanza");
		} else {
			System.out.println("Non e stato possibile posare attrezzo nella stanza");
		}
	}
	
	
}
