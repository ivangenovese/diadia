package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class PartitaTest {
	
	private Partita partita;
	private Attrezzo attrezzo;
	
	@Before
	public void setUp() throws Exception {
		this.partita = new Partita();
		this.attrezzo = new Attrezzo("attrezzo", 1);
	}
	
	@Test
	public void testVinta_nonVinta() {
		assertFalse(this.partita.vinta());
	}
	
	@Test 
	
	public void testVinta_finitiCfu() {
		this.partita.getGiocatore().setCfu(0);
		assertFalse(this.partita.vinta());
	}
	
	@Test
	public void testVinta_stanzeUguali() {
		this.partita.getLabirinto().setStanzaCorrente(this.partita.getLabirinto().getStanzaFinale());
		assertTrue(this.partita.vinta());
	}

	@Test
	public void testIsFinita_nonFinita() {
		assertFalse(this.partita.isFinita());
	}

	@Test
	public void testPrendiAttrezzo() {
		this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(this.attrezzo);
		this.partita.prendiAttrezzo(this.attrezzo.getNome());
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo(this.attrezzo.getNome()));
	}

	@Test
	public void testPosaAttrezzo() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(this.attrezzo);
		this.partita.posaAttrezzo(this.attrezzo.getNome());
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo(this.attrezzo.getNome()));
	}
	
}
