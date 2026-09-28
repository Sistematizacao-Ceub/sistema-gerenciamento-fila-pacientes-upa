package br.ceub.poo.upa.estrutura;

import java.util.List;

/**
 * Pilha LIFO (Last In, First Out) com lista encadeada.
 * Usada no HISTÓRICO DE ATENDIMENTOS: o último atendimento aparece primeiro.
 *
 *   topo ──► [C] ──► [B] ──► [A] ──► null
 *   empilhar() e desempilhar() operam SEMPRE no topo. Ambos O(1).
 *
 * TODO [Etapa 4]: implementar TODOS os métodos.
 *   - empilhar(valor): novo nó aponta para o topo atual e vira o novo topo.
 *   - desempilhar(): remove e devolve o topo; FilaVaziaException se vazia.
 *   - topo(): devolve sem remover; FilaVaziaException se vazia.
 *   - paraLista(): do TOPO para a BASE (mais recente primeiro).
 */
public class Pilha<T> implements Colecao<T> {

    // TODO: atributos (referência para o topo e contador de tamanho)

    public void empilhar(T valor) {
        // TODO
        throw new UnsupportedOperationException("TODO: Pilha.empilhar()");
    }

    public T desempilhar() {
        // TODO
        throw new UnsupportedOperationException("TODO: Pilha.desempilhar()");
    }

    public T topo() {
        // TODO
        throw new UnsupportedOperationException("TODO: Pilha.topo()");
    }

    @Override
    public int tamanho() {
        // TODO
        throw new UnsupportedOperationException("TODO: Pilha.tamanho()");
    }

    @Override
    public boolean estaVazia() {
        // TODO
        throw new UnsupportedOperationException("TODO: Pilha.estaVazia()");
    }

    @Override
    public List<T> paraLista() {
        // TODO
        throw new UnsupportedOperationException("TODO: Pilha.paraLista()");
    }
}
