package br.ceub.poo.upa.excecao;

/**
 * Exceção BASE (checked) de todas as regras de negócio da UPA.
 *
 * <p>CONCEITO: hierarquia de exceções. Ao herdar de {@link Exception}, esta é uma
 * exceção <b>verificada</b> (checked): o compilador OBRIGA quem chama o método a
 * tratá-la (try/catch) ou declará-la (throws). Usamos checked para erros que o
 * usuário pode corrigir (CPF digitado errado, sinal vital fora da faixa etc.).</p>
 *
 * <p>JÁ IMPLEMENTADA — use como modelo para as demais.</p>
 */
public class UpaException extends Exception {

    public UpaException(String mensagem) {
        super(mensagem);
    }
}
