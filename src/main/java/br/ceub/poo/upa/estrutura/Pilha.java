package br.ceub.poo.upa.estrutura;

import br.ceub.poo.upa.excecao.FilaVaziaException;

import java.util.ArrayList;
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

    private No<T> topo;
    private int tamanho;

    public void empilhar(T valor) {
        No<T> novo = new No<>(valor);
        novo.proximo = topo;
        topo = novo;
        tamanho++;
    }

    public T desempilhar() {
        if (estaVazia()) {
            throw new FilaVaziaException("A pilha está vazia");
        }
        T valor = topo.valor;
        topo = topo.proximo;
        tamanho--;
        return valor;
    }

    public T topo() {
        if (estaVazia()) {
            throw new FilaVaziaException("A pilha está vazia");
        }
        return topo.valor;
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
        ArrayList<T> lista = new ArrayList<>();
        for(No<T> atual = topo; atual != null; atual = atual.proximo ) {
            lista.add(atual.valor);
        }
        return lista;
    }
}
