package br.ceub.poo.upa.excecao;

/**
 * Lançada ao tentar retirar/espiar um elemento de uma fila ou pilha vazia.
 *
 * <p>CONCEITO: exceção <b>não verificada</b> (unchecked). Herda de
 * {@link RuntimeException}, portanto o compilador NÃO obriga o tratamento.
 * Faz sentido aqui porque é um erro de PROGRAMAÇÃO: o código deveria ter
 * verificado {@code estaVazia()} antes de chamar {@code desenfileirar()} —
 * é o mesmo raciocínio de {@link java.util.NoSuchElementException}.</p>
 *
 * <p>JÁ IMPLEMENTADA.</p>
 */
public class FilaVaziaException extends RuntimeException {

    public FilaVaziaException(String mensagem) {
        super(mensagem);
    }
}
