package br.ceub.poo.upa.excecao;

/**
 * Lançada ao tentar cadastrar um CPF que JÁ está ativo (aguardando triagem ou atendimento).
 * JÁ IMPLEMENTADA.
 */
public class PacienteDuplicadoException extends UpaException {

    public PacienteDuplicadoException(String cpf) {
        super("Já existe um paciente ativo com o CPF " + cpf);
    }
}
