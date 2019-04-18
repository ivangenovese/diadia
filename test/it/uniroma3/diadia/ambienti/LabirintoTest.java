package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LabirintoTest {
	
	private Stanza stanzaCorrente;
	private Stanza stanzaFinale;
	private Labirinto labirinto;
	
	@Before
	public void setUp() throws Exception {
		this.stanzaCorrente = new Stanza("N11");
		this.stanzaFinale = new Stanza("Biblioteca");
		this.labirinto = new Labirinto(this.stanzaCorrente, this.stanzaFinale);
	}

	
	@Test
	public void testStanzeUguali_uguali() {
		assertEquals(this.initLabirinto(), this.labirinto);
	}
	
	@Test
	public void testStanzeUguali_diverse() {
		assertNotEquals(this.initLabirinto(), this.initDiversoLabirinto());
	}
	
	
	public Labirinto initLabirinto() {
		return new Labirinto(new Stanza("N11"), new Stanza("Biblioteca"));
	}
	
	public Labirinto initDiversoLabirinto() {
		return new Labirinto(new Stanza("N10"), new Stanza("Campus One"));
	}
	
}
