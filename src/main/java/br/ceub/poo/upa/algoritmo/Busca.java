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
     * BUSCA SEQUENCIAL — O(n). JÁ IMPLEMENTADA como referência.
     * Funciona em listas NÃO ordenadas.
     *
     * @return índice do primeiro elemento que satisfaz o critério, ou -1.
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
     * BUSCA BINÁRIA — O(log n). Exige lista ORDENADA pela chave.
     *
     * @param lista          lista ordenada em ordem CRESCENTE de chave
     * @param chave          valor procurado (ex.: número da senha)
     * @param extratorChave  como obter a chave de um elemento (ex.: Paciente::getSenha)
     * @return índice do elemento, ou -1 se não existir
     */
    public static <T> int buscaBinaria(List<T> lista, int chave, ToIntFunction<T> extratorChave) {
        return buscaBinariaRecursiva(lista, chave, extratorChave, 0, lista.size() - 1);
    }

    /**
     * TODO [Etapa 5]: implementar de forma RECURSIVA.
     *   CASO BASE 1: inicio > fim  → return -1 (não achou)
     *   meio = inicio + (fim - inicio) / 2      ← evita overflow de (inicio+fim)/2
     *   valorMeio = extratorChave.applyAsInt(lista.get(meio))
     *   CASO BASE 2: valorMeio == chave → return meio
     *   se chave < valorMeio → busque na metade ESQUERDA (inicio, meio - 1)
     *   senão                → busque na metade DIREITA  (meio + 1, fim)
     */
    private static <T> int buscaBinariaRecursiva(List<T> lista, int chave, ToIntFunction<T> extratorChave,
                                                 int inicio, int fim) {
        // TODO
        throw new UnsupportedOperationException("TODO: Busca.buscaBinariaRecursiva()");
    }
}
