package br.ceub.poo.upa.excecao;

/**
 * Lançada quando uma busca (por CPF ou por senha) não encontra o paciente.
 *
 * TODO [Etapa 5]: implemente esta exceção seguindo o modelo de {@link CpfInvalidoException}.
 *   - Deve herdar de UpaException (checked).
 *   - Construtor recebe a chave buscada (String) e monta a mensagem:
 *     "Paciente não encontrado: <chave>"
 */
public class PacienteNaoEncontradoException extends UpaException {

    public PacienteNaoEncontradoException(String chave) {
        super("TODO"); // TODO: montar a mensagem descrita acima
    }
}
