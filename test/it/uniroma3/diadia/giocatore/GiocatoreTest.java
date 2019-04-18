package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class GiocatoreTest {
	
	private Giocatore giocatore;
	private Giocatore giocatoreNonIstanziato;
	private Attrezzo osso;
	private Attrezzo attrezzoPieno;
	
	@Before
	public void setUp() throws Exception {
		this.giocatore = new Giocatore("Ivan");
		this.osso = new Attrezzo("osso", 3);
		this.attrezzoPieno = new Attrezzo("attrezzoPieno", this.giocatore.getBorsa().getPesoMax());
	}

	@Test
	public void testGiocatoreIstanziato_presente() {
		assertTrue(this.giocatore != null);
	}
	
	@Test
	public void testGiocatoreIstanziato_nonPresente() {
		assertTrue(this.giocatoreNonIstanziato == null);
	}

	@Test
	public void testDecrementaCfu_cfuMaggioreDiZero() {
		int cfuPrima = this.giocatore.getCfu();
		this.giocatore.decrementaCfu();
		assertNotEquals(cfuPrima, this.giocatore.getCfu());
	}
	
	@Test
	public void testDecrementaCfu_zeroCfu() {
		this.giocatore.setCfu(0);
		this.giocatore.decrementaCfu();
		assertEquals(0, this.giocatore.getCfu());
	}
	
	@Test
	public void testPrendereAttrezzo_borsaNonPiena() {
		assertTrue(this.giocatore.getBorsa().addAttrezzo(this.osso));
	}
	
	@Test
	public void testPrendereAttrezzo_borsaPiena() {
		this.giocatore.getBorsa().addAttrezzo(this.attrezzoPieno);
		assertFalse(this.giocatore.getBorsa().addAttrezzo(new Attrezzo("attrezzo", 1)));
	}
	
}
