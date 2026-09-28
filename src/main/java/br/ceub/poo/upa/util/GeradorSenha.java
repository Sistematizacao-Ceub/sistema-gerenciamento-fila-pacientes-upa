package br.ceub.poo.upa.util;

/**
 * Gera senhas sequenciais (1, 2, 3...). JÁ IMPLEMENTADO.
 * <p>SRP: a responsabilidade de "numerar" fica isolada aqui, não no serviço.</p>
 */
public class GeradorSenha {

    private int ultima;

    public int proxima() {
        return ++ultima;
    }
}
