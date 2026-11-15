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
     * MERGE SORT (recursivo, O(n log n), estável).
     * Devolve uma NOVA lista ordenada; a lista original NÃO é alterada.
     */
    public static <T> List<T> mergeSort(List<T> lista, Comparator<? super T> comparador) {

        // Caso base: lista vazia ou com apenas um elemento
        if (lista.size() <= 1) {
            return new ArrayList<>(lista);
        }

        // Divide a lista ao meio
        int meio = lista.size() / 2;

        // Ordena recursivamente a metade esquerda
        List<T> esquerda = mergeSort(
            new ArrayList<>(lista.subList(0, meio)),
            comparador
        );

        // Ordena recursivamente a metade direita
        List<T> direita = mergeSort(
            new ArrayList<>(lista.subList(meio, lista.size())),
            comparador
        );

        // Intercala as duas metades ordenadas
        return intercalar(esquerda, direita, comparador);
    }

    /**
     * Intercala duas listas já ordenadas em uma única lista ordenada.
     */
    static <T> List<T> intercalar(
            List<T> esquerda,
            List<T> direita,
            Comparator<? super T> comparador) {

        List<T> resultado = new ArrayList<>(esquerda.size() + direita.size());

        int i = 0;
        int j = 0;

        // Compara os elementos enquanto ambas as listas tiverem elementos
        while (i < esquerda.size() && j < direita.size()) {

            if (comparador.compare(esquerda.get(i), direita.get(j)) <= 0) {
                resultado.add(esquerda.get(i));
                i++;
            } else {
                resultado.add(direita.get(j));
                j++;
            }
        }

        // Adiciona os elementos restantes da esquerda
        while (i < esquerda.size()) {
            resultado.add(esquerda.get(i));
            i++;
        }

        // Adiciona os elementos restantes da direita
        while (j < direita.size()) {
            resultado.add(direita.get(j));
            j++;
        }

        return resultado;
    }

    /**
     * INSERTION SORT (iterativo, O(n²)).
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