package br.ceub.poo.upa.algoritmo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Testes FORNECIDOS pelo professor. Todos devem passar ao final. */
class OrdenacaoTest {

    @Test
    void ordenaInteiros() {
        List<Integer> entrada = List.of(5, 2, 9, 1, 5, 6, 0);
        assertEquals(List.of(0, 1, 2, 5, 5, 6, 9), Ordenacao.mergeSort(entrada, Comparator.naturalOrder()));
    }

    @Test
    void listaVaziaEUnitaria() {
        assertEquals(List.of(), Ordenacao.mergeSort(new ArrayList<Integer>(), Comparator.naturalOrder()));
        assertEquals(List.of(7), Ordenacao.mergeSort(List.of(7), Comparator.naturalOrder()));
    }

    @Test
    void naoAlteraAListaOriginal() {
        List<Integer> original = new ArrayList<>(List.of(3, 1, 2));
        Ordenacao.mergeSort(original, Comparator.naturalOrder());
        assertEquals(List.of(3, 1, 2), original);
    }

    @Test
    void ehEstavel() {
        // ordena pelo tamanho da palavra; empates devem manter a ordem original
        List<String> palavras = List.of("bb", "a", "cc", "d", "ee");
        assertEquals(List.of("a", "d", "bb", "cc", "ee"),
                Ordenacao.mergeSort(palavras, Comparator.comparingInt(String::length)));
    }

    @Test
    void mesmoResultadoQueInsertionSort() {
        List<Integer> entrada = List.of(42, -3, 17, 8, 8, 0, 99, -50, 23);
        assertEquals(Ordenacao.insertionSort(entrada, Comparator.reverseOrder()),
                Ordenacao.mergeSort(entrada, Comparator.reverseOrder()));
    }
}
