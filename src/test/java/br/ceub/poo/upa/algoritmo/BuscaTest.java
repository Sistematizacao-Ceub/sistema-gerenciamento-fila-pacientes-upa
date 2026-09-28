package br.ceub.poo.upa.algoritmo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Testes FORNECIDOS pelo professor. Todos devem passar ao final. */
class BuscaTest {

    private final List<Integer> ordenada = List.of(1, 3, 5, 7, 9, 11, 13);

    @Test
    void encontraNoInicioMeioEFim() {
        assertEquals(0, Busca.buscaBinaria(ordenada, 1, Integer::intValue));
        assertEquals(3, Busca.buscaBinaria(ordenada, 7, Integer::intValue));
        assertEquals(6, Busca.buscaBinaria(ordenada, 13, Integer::intValue));
    }

    @Test
    void naoEncontraRetornaMenosUm() {
        assertEquals(-1, Busca.buscaBinaria(ordenada, 4, Integer::intValue));
        assertEquals(-1, Busca.buscaBinaria(ordenada, 99, Integer::intValue));
        assertEquals(-1, Busca.buscaBinaria(List.<Integer>of(), 1, Integer::intValue));
    }
}
