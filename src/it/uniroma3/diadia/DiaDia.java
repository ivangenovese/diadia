package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO (da un'idea di Michael Kolling and David J. Barnes)
 * 
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa", "stampa" };

	private Partita partita;
	private static Scanner scannerDiLinee = new Scanner(System.in);

	public DiaDia() {
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione;

		System.out.println(MESSAGGIO_BENVENUTO);

		do
			istruzione = scannerDiLinee.nextLine();
		while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */////////////////////////////////////////////////////////////////////
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome() == null) {
			return false;
		}

		switchComandi(comandoDaEseguire);

		return checkPartitaVinta();
	}

	
	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 *////////////////////////////////////////////////////////////////////
	public boolean switchComandi(Comando comandoDaEseguire) {
		switch (comandoDaEseguire.getNome()) {
		case "aiuto": // Comando: aiuto (lista comandi)
			this.aiuto();
			break;
		case "fine": // Comando: fine partita
			this.fine();
			return true;
		case "vai": // Comando: vai
			this.vai(comandoDaEseguire.getParametro());
			break;
		case "stampa": // Comando: stampa borsa, stampa stanza
			this.stampa(comandoDaEseguire.getParametro());
			break;
		case "posa": // Comando: posa <nomeAttrezzo>
			this.posa(comandoDaEseguire.getParametro());
			break;
		case "prendi": // Comando: prendi <nomeAttrezzo>
			this.prendi(comandoDaEseguire.getParametro());
			break;
		default: // Comando sconosciuto
			System.out.println("Comando sconosciuto");
			break;
		}
		return false;
	}
	
	/**
	 * Controllo se la partita e vinta
	 * @return
	 */
	public boolean checkPartitaVinta() {
		if (this.partita.vinta()) {
			System.out.println("Hai vinto!");
			return true;
		} else
			return false;
	}

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		System.out.println("Lista comandi:");
		for (int i = 0; i < elencoComandi.length; i++)
			System.out.print(elencoComandi[i] + " ");
		System.out.println();
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa il
	 * nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if (direzione == null) 
		System.out.println("Dove vuoi andare ?");
		else {
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			System.out.println("Direzione inesistente");
		else {
			this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			this.partita.getGiocatore().decrementaCfu();
			System.out.println(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		}}
		
	}

	/**
	 * Comando "Fine partita".
	 */
	private void fine() {
		System.out.println("Grazie di aver giocato!"); // si desidera smettere
	}

	/**
	 * Prendi attrezzo dalla stanza
	 * 
	 * @param parametro
	 *            nome attrezzo
	 */
	private void prendi(String parametro) {

		if (parametro == null) {
			System.out.println("Non e stato inserito il nome dell\'attrezzo");
			return;
		}

		this.partita.prendiAttrezzo(parametro);

	}

	/**
	 * Posare attrezzi nella stanza
	 * 
	 * @param parametro
	 *            nome attrezzo
	 */
	private void posa(String parametro) {
		if (parametro == null) {
			System.out.println("Non e stato inserito il nome dell\'attrezzo");
			return;
		}

		this.partita.posaAttrezzo(parametro);

	}

	/**
	 * Comando stampa
	 * 
	 * @param parametro
	 */
	private void stampa(String parametro) {
		
		if (parametro == null) {
			System.out.println(this.partita.getGiocatore().getBorsa().toString());
			System.out.println(this.partita.getLabirinto().getStanzaCorrente().toString());
			return;
		}

		switch (parametro) {
		case "borsa": // comando: stampa borsa
			System.out.println(this.partita.getGiocatore().getBorsa().toString());
			break;
		case "stanza": // comando: stampa stanza corrente
			System.out.println(this.partita.getLabirinto().getStanzaCorrente().toString());
			break;
		default:
			System.out.println("Comando sconosciuto");
			break;
		}
	}

	/**
	 * Funzione main per esecuzione iniziale del programma
	 * 
	 * @param argc
	 */
	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}

}