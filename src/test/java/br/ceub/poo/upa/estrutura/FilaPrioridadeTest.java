package br.ceub.poo.upa.estrutura;

import br.ceub.poo.upa.excecao.FilaVaziaException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Testes FORNECIDOS pelo professor. Todos devem passar ao final. */
class FilaPrioridadeTest {

    /** Elemento de teste: compara SÓ pela prioridade; o id serve para verificar a estabilidade. */
    private static final class Item implements Comparable<Item> {
        final int prioridade;
        final String id;

        Item(int prioridade, String id) {
            this.prioridade = prioridade;
            this.id = id;
        }

        @Override
        public int compareTo(Item outro) {
            return Integer.compare(prioridade, outro.prioridade);
        }
    }

    @Test
    void novaFilaEstaVazia() {
        FilaPrioridade<Item> fila = new FilaPrioridade<>();
        assertTrue(fila.estaVazia());
        assertEquals(0, fila.tamanho());
    }

    @Test
    void removeSempreOMaisPrioritario() {
        FilaPrioridade<Item> fila = new FilaPrioridade<>();
        fila.enfileirar(new Item(3, "c"));
        fila.enfileirar(new Item(0, "a"));   // vai para o INÍCIO
        fila.enfileirar(new Item(4, "d"));   // vai para o FIM
        fila.enfileirar(new Item(1, "b"));   // vai para o MEIO
        assertEquals(4, fila.tamanho());
        assertEquals("a", fila.espiar().id);
        assertEquals("a", fila.desenfileirar().id);
        assertEquals("b", fila.desenfileirar().id);
        assertEquals("c", fila.desenfileirar().id);
        assertEquals("d", fila.desenfileirar().id);
        assertTrue(fila.estaVazia());
    }

    @Test
    void empateRespeitaOrdemDeChegada() {
        FilaPrioridade<Item> fila = new FilaPrioridade<>();
        fila.enfileirar(new Item(2, "primeiro"));
        fila.enfileirar(new Item(2, "segundo"));
        fila.enfileirar(new Item(2, "terceiro"));
        List<Item> ordem = fila.paraLista();
        assertEquals("primeiro", ordem.get(0).id);
        assertEquals("segundo", ordem.get(1).id);
        assertEquals("terceiro", ordem.get(2).id);
    }

    @Test
    void paraListaNaoAlteraAFila() {
        FilaPrioridade<Item> fila = new FilaPrioridade<>();
        fila.enfileirar(new Item(1, "x"));
        fila.paraLista().clear();
        assertEquals(1, fila.tamanho());
    }

    @Test
    void filaVaziaLancaExcecao() {
        FilaPrioridade<Item> fila = new FilaPrioridade<>();
        assertThrows(FilaVaziaException.class, fila::desenfileirar);
        assertThrows(FilaVaziaException.class, fila::espiar);
    }
}
