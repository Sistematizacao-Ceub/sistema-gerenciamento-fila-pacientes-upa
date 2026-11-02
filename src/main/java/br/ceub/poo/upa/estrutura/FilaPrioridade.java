package br.ceub.poo.upa.estrutura;

import br.ceub.poo.upa.excecao.FilaVaziaException;

import java.util.ArrayList;
import java.util.List;

/**
 * Fila de PRIORIDADE implementada com lista encadeada ORDENADA.
 * Usada na FILA DE ATENDIMENTO MÉDICO.
 *
 * <p>{@code <T extends Comparable<T>>} = GENÉRICO LIMITADO: só aceita tipos que sabem
 * se comparar (como {@code Paciente}, que implementa {@code Comparable<Paciente>}).</p>
 *
 * <p>Estratégia: manter a lista SEMPRE ordenada no momento da inserção.
 * Assim, o próximo a ser atendido está sempre no INÍCIO.</p>
 * <ul>
 *   <li>enfileirar: O(n) — percorre até achar a posição correta;</li>
 *   <li>desenfileirar / espiar: O(1) — remove/lê o início.</li>
 * </ul>
 *
 * TODO [Etapa 4]: implementar TODOS os métodos (use a classe {@link Fila} como referência).
 *
 * ATENÇÃO — ESTABILIDADE: se dois elementos forem "iguais" (compareTo == 0), o que
 * chegou PRIMEIRO deve sair PRIMEIRO. Portanto, ao inserir, avance ENQUANTO
 * {@code atual.proximo.valor.compareTo(novo) <= 0}  (note o "<=", não "<").
 *
 * Casos que o enfileirar precisa tratar:
 *   (1) fila vazia;
 *   (2) novo elemento vai para o INÍCIO (mais prioritário que o atual primeiro);
 *   (3) novo elemento vai para o MEIO ou para o FIM.
 */
public class FilaPrioridade<T extends Comparable<T>> implements Colecao<T> {

    private No<T> inicio;
    private int tamanho;

    public void enfileirar(T valor) {
        No<T> novo = new No<>(valor);
        if (estaVazia()) {
            inicio = novo;
        } else if (novo.valor.compareTo(inicio.valor) < 0) {
            novo.proximo = inicio;
            inicio = novo;
        } else {
            No<T> cursor = inicio;
            while (cursor.proximo != null && cursor.proximo.valor.compareTo(novo.valor) <= 0) {
                cursor = cursor.proximo;
            }
            novo.proximo = cursor.proximo;
            cursor.proximo = novo;
        }
        tamanho++;
    }

    /** Remove e devolve o elemento MAIS prioritário. Lança FilaVaziaException se vazia. */
    public T desenfileirar() {
        if(estaVazia()) {
            throw new FilaVaziaException("A fila está vazia");
        }
        T valor = inicio.valor;
        inicio = inicio.proximo;
        tamanho--;
        return valor;
    }

    /** Devolve (sem remover) o elemento MAIS prioritário. Lança FilaVaziaException se vazia. */
    public T espiar() {
        if(estaVazia()) {
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
        return this.tamanho == 0;
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
