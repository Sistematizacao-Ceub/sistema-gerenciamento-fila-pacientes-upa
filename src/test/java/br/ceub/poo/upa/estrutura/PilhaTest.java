package br.ceub.poo.upa.estrutura;

import br.ceub.poo.upa.excecao.FilaVaziaException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Testes FORNECIDOS pelo professor. Todos devem passar ao final. */
class PilhaTest {

    @Test
    void ultimoAEntrarEhPrimeiroASair() {
        Pilha<String> pilha = new Pilha<>();
        pilha.empilhar("A");
        pilha.empilhar("B");
        pilha.empilhar("C");
        assertEquals(List.of("C", "B", "A"), pilha.paraLista());
        assertEquals("C", pilha.topo());
        assertEquals("C", pilha.desempilhar());
        assertEquals("B", pilha.desempilhar());
        assertEquals(1, pilha.tamanho());
    }

    @Test
    void pilhaVaziaLancaExcecao() {
        Pilha<String> pilha = new Pilha<>();
        assertTrue(pilha.estaVazia());
        assertThrows(FilaVaziaException.class, pilha::desempilhar);
        assertThrows(FilaVaziaException.class, pilha::topo);
    }
}
