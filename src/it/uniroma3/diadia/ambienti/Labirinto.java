package it.uniroma3.diadia.ambienti;

public class Labirinto {
	
	private Stanza stanzaCorrente;
	private Stanza stanzaFinale;
	
	/**
	 * Construttore del labirinto
	 * @param start
	 * @param finish
	 */
	public Labirinto(Stanza start, Stanza finish) {
		this.setStanzaCorrente(start);
		this.setStanzaFinale(finish);
	}
	
	/*
	 * Controllo se le due stanze sono uguali
	 * @return
	 *///////////////////////////////////////
	
	public boolean stanzeUguali() {
		return this.getStanzaCorrente().equals(this.getStanzaFinale());
	}
	
	
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	public void setStanzaCorrente(Stanza stanzaIniziale) {
		this.stanzaCorrente = stanzaIniziale;
	}

	public Stanza getStanzaFinale() {
		return this.stanzaFinale;
	}

	public void setStanzaFinale(Stanza stanzaFinale) {
		this.stanzaFinale = stanzaFinale;
	}
	//Mio priomo commento
	@Override
	public boolean equals(Object o) {
		Labirinto l = (Labirinto) o;
		return this.getStanzaCorrente().equals(l.getStanzaCorrente()) && 
				this.getStanzaFinale().equals(l.getStanzaFinale());
	}
	
}
