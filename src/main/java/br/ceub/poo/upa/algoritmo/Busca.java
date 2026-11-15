package br.ceub.poo.upa.algoritmo;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

/**
 * Algoritmos de BUSCA.
 */
public final class Busca {

    private Busca() {
    }

    /**
     * BUSCA SEQUENCIAL — O(n).
     * Funciona em listas NÃO ordenadas.
     */
    public static <T> int buscaSequencial(List<T> lista, Predicate<T> criterio) {
        for (int i = 0; i < lista.size(); i++) {
            if (criterio.test(lista.get(i))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * BUSCA BINÁRIA — O(log n).
     * Exige lista ORDENADA em ordem crescente.
     */
    public static <T> int buscaBinaria(
            List<T> lista,
            int chave,
            ToIntFunction<T> extratorChave) {

        return buscaBinariaRecursiva(
                lista, chave, extratorChave, 0, lista.size() - 1);
    }

    /**
     * Busca binária implementada de forma recursiva.
     */
    private static <T> int buscaBinariaRecursiva(
            List<T> lista,
            int chave,
            ToIntFunction<T> extratorChave,
            int inicio,
            int fim) {

        // Caso base: elemento não encontrado
        if (inicio > fim) {
            return -1;
        }

        // Calcula o meio da lista
        int meio = inicio + (fim - inicio) / 2;

        // Obtém a chave do elemento central
        int valorMeio = extratorChave.applyAsInt(lista.get(meio));

        // Elemento encontrado
        if (valorMeio == chave) {
            return meio;
        }

        // Busca na metade esquerda
        if (chave < valorMeio) {
            return buscaBinariaRecursiva(
                    lista, chave, extratorChave, inicio, meio - 1);
        }

        // Busca na metade direita
        return buscaBinariaRecursiva(
                lista, chave, extratorChave, meio + 1, fim);
    }
}