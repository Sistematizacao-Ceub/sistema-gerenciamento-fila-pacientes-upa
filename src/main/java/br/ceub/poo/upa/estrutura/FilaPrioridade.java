package br.ceub.poo.upa.estrutura;

import br.ceub.poo.upa.excecao.FilaVaziaException;

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
        // TODO
        throw new UnsupportedOperationException("TODO: FilaPrioridade.enfileirar()");
    }

    /** Remove e devolve o elemento MAIS prioritário. Lança FilaVaziaException se vazia. */
    public T desenfileirar() {
        // TODO
        throw new UnsupportedOperationException("TODO: FilaPrioridade.desenfileirar()");
    }

    /** Devolve (sem remover) o elemento MAIS prioritário. Lança FilaVaziaException se vazia. */
    public T espiar() {
        // TODO
        throw new UnsupportedOperationException("TODO: FilaPrioridade.espiar()");
    }

    @Override
    public int tamanho() {
        // TODO
        throw new UnsupportedOperationException("TODO: FilaPrioridade.tamanho()");
    }

    @Override
    public boolean estaVazia() {
        // TODO
        throw new UnsupportedOperationException("TODO: FilaPrioridade.estaVazia()");
    }

    @Override
    public List<T> paraLista() {
        // TODO
        throw new UnsupportedOperationException("TODO: FilaPrioridade.paraLista()");
    }
}
