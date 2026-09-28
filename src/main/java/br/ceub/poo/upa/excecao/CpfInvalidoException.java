package br.ceub.poo.upa.excecao;

/** Lançada quando o CPF informado não passa na validação dos dígitos verificadores. JÁ IMPLEMENTADA. */
public class CpfInvalidoException extends UpaException {

    public CpfInvalidoException(String cpf) {
        super("CPF inválido: " + cpf);
    }
}
