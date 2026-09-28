package br.ceub.poo.upa.estrutura;

import br.ceub.poo.upa.excecao.FilaVaziaException;

import java.util.ArrayList;
import java.util.List;

/**
 * Fila FIFO (First In, First Out) implementada com lista encadeada.
 * Usada na FILA DE TRIAGEM: quem chega primeiro é triado primeiro.
 *
 *   inicio ──► [A] ──► [B] ──► [C] ◄── fim
 *   desenfileirar() remove do INÍCIO;  enfileirar() insere no FIM. Ambos O(1).
 *
 * <p>JÁ IMPLEMENTADA — ESTUDE ESTA CLASSE: ela é o modelo para você implementar
 * {@link FilaPrioridade} e {@link Pilha}. É PROIBIDO usar java.util.Queue,
 * LinkedList, PriorityQueue, ArrayDeque ou Stack nas estruturas deste pacote.</p>
 */
public class Fila<T> implements Colecao<T> {

    private No<T> inicio;
    private No<T> fim;
    private int tamanho;

    public void enfileirar(T valor) {
        No<T> novo = new No<>(valor);
        if (estaVazia()) {
            inicio = novo;
        } else {
            fim.proximo = novo;
        }
        fim = novo;
        tamanho++;
    }

    public T desenfileirar() {
        if (estaVazia()) {
            throw new FilaVaziaException("A fila está vazia");
        }
        T valor = inicio.valor;
        inicio = inicio.proximo;
        if (inicio == null) {
            fim = null;
        }
        tamanho--;
        return valor;
    }

    public T espiar() {
        if (estaVazia()) {
            throw new FilaVaziaException("A fila está vazia");
        }
        return inicio.valor;
    }

    @Override
    public int tamanho() {
        return tamanho;
    }

    @Override
    public boolean estaVazia() {
        return tamanho == 0;
    }

    @Override
    public List<T> paraLista() {
        List<T> lista = new ArrayList<>();
        for (No<T> atual = inicio; atual != null; atual = atual.proximo) {
            lista.add(atual.valor);
        }
        return lista;
    }
}
