package br.ceub.poo.upa.excecao;

/**
 * Lançada ao tentar chamar o próximo paciente para atendimento sem informar o médico.
 */
public class MedicoNaoInformadoException extends UpaException {

    public MedicoNaoInformadoException() {
        super("Médico não encontrado");
    }
}
