package br.ceub.poo.upa.algoritmo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Algoritmos de ORDENAÇÃO escritos à mão.
 * PROIBIDO usar Collections.sort(), List.sort(), Arrays.sort() ou stream().sorted().
 *
 * <p>{@link Comparator} permite ordenar o MESMO tipo por critérios diferentes
 * (por espera, por nome, por senha...) — outro exemplo de Strategy.</p>
 */
public final class Ordenacao {

    private Ordenacao() {
        // classe utilitária: só métodos estáticos, não deve ser instanciada
    }

    /**
     * MERGE SORT (recursivo, O(n log n), estável). Devolve uma NOVA lista ordenada;
     * a lista original NÃO é alterada.
     *
     * TODO [Etapa 5]:
     *   CASO BASE: se a lista tem 0 ou 1 elemento → devolva uma cópia dela.
     *   PASSO RECURSIVO:
     *     1. meio = tamanho / 2
     *     2. esquerda = mergeSort(sublista [0, meio), comparador)      ← recursão
     *     3. direita  = mergeSort(sublista [meio, tamanho), comparador) ← recursão
     *     4. return intercalar(esquerda, direita, comparador)
     *   Dica: new ArrayList<>(lista.subList(a, b))
     */
    public static <T> List<T> mergeSort(List<T> lista, Comparator<? super T> comparador) {
        if (lista.size() <= 1) return lista;
        List<T> copia = new ArrayList<>(lista);
        int meio = copia.size() / 2;
        List<T> esquerdaOrdenada = mergeSort(copia.subList(0, meio), comparador);
        List<T> direitaOrdenada = mergeSort(copia.subList(meio, copia.size()), comparador);
        return intercalar(esquerdaOrdenada, direitaOrdenada, comparador);

    }

    /**
     * Intercala duas listas JÁ ORDENADAS em uma única lista ordenada.
     * Use "<= 0" ao comparar para manter a ESTABILIDADE (empates preservam a ordem original).
     *
     * TODO [Etapa 5]: dois índices (i para esquerda, j para direita); enquanto ambos
     * tiverem elementos, copie o menor; ao final, copie o que sobrou de cada lado.
     */
    static <T> List<T> intercalar(List<T> esquerda, List<T> direita, Comparator<? super T> comparador) {
        List<T> resultado = new ArrayList<>(esquerda.size() + direita.size());
        int i = 0;
        int j = 0;
        while (i < esquerda.size() && j < direita.size()) {
            if (comparador.compare(esquerda.get(i), direita.get(j)) <= 0) {
                resultado.add(esquerda.get(i++));
            } else {
                resultado.add(direita.get(j++));
            }
        }
        while (i < esquerda.size()) {
            resultado.add(esquerda.get(i++));
        }
        while (j < direita.size()) {
            resultado.add(direita.get(j++));
        }
        return resultado;
    }

    /**
     * INSERTION SORT (iterativo, O(n²)) — JÁ IMPLEMENTADO como referência.
     * Compare com o merge sort: qual é mais rápido para 10 elementos? E para 100.000?
     */
    public static <T> List<T> insertionSort(List<T> lista, Comparator<? super T> comparador) {
        List<T> copia = new ArrayList<>(lista);
        for (int i = 1; i < copia.size(); i++) {
            T chave = copia.get(i);
            int j = i - 1;
            while (j >= 0 && comparador.compare(copia.get(j), chave) > 0) {
                copia.set(j + 1, copia.get(j));
                j--;
            }
            copia.set(j + 1, chave);
        }
        return copia;
    }
}
