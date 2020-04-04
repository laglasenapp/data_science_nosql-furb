package br.furb.bingo.db;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class AcoesDBTest {

	@Test
	void testAcoesDBValoresRandomicos() {
		AcoesDB acoes = new AcoesDbImpl();
		int actual = acoes.obterValoresRandomicos().size();
		int expected = 15;
		assertEquals(expected, actual);
	}

}
