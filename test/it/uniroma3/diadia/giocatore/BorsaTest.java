package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {
	
	private Borsa borsa;
	private Attrezzo osso;
	private Attrezzo chiave;
	private Attrezzo attrezzoNonPresente;
	private Attrezzo attrezzoPesoMassimo;
	
	@Before
	public void setUp() throws Exception {
		this.borsa = new Borsa();
		this.osso = new Attrezzo("osso",5);
		this.chiave = new Attrezzo("chiave", 5);
		this.attrezzoNonPresente = new Attrezzo("nonPresente", 5);
		this.attrezzoPesoMassimo = new Attrezzo("attrezzoPesoMassimo", this.borsa.getPesoMax());
		this.borsa.addAttrezzo(osso);
		this.borsa.addAttrezzo(chiave);
	}
	
	@Test
	public void testHasAttrezzo_presente() {
		assertTrue(this.borsa.hasAttrezzo(this.osso.getNome()));
	}
	
	@Test
	public void testHasAttrezzo_nonPresente() {
		assertFalse(this.borsa.hasAttrezzo(this.attrezzoNonPresente.getNome()));
	}
	
	@Test
	public void testIsPieno_pieno() {
		this.borsa.addAttrezzo(this.attrezzoPesoMassimo);
		assertTrue(this.borsa.isPieno());
	}
	
	@Test
	public void testIsPieno_nonPieno() {
		assertFalse(new Borsa().isPieno());
	}
	
	@Test
	public void testIsVuoto_vuoto() {
		assertTrue(new Borsa().isEmpty());
	}
	
}
