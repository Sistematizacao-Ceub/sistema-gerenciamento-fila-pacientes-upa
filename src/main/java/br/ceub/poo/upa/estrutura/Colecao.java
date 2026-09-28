package br.ceub.poo.upa.estrutura;

import java.util.List;

/**
 * Operações comuns a todas as estruturas de dados do projeto.
 * <p>Interface GENÉRICA ({@code <T>}): funciona para qualquer tipo. JÁ IMPLEMENTADA.</p>
 */
public interface Colecao<T> {

    int tamanho();

    boolean estaVazia();

    /** Cópia dos elementos, na ordem em que seriam retirados. Alterar a lista NÃO altera a estrutura. */
    List<T> paraLista();
}
