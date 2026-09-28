package br.ceub.poo.upa.excecao;

/** Lançada quando algum sinal vital está fora da faixa fisiologicamente possível. JÁ IMPLEMENTADA. */
public class SinaisVitaisInvalidosException extends UpaException {

    public SinaisVitaisInvalidosException(String mensagem) {
        super(mensagem);
    }
}
