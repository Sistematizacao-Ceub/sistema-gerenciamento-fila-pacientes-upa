package br.ceub.poo.upa.modelo;

import java.time.LocalDate;

/**
 * Médico(a) responsável pelo ATENDIMENTO após a triagem.
 *
 * TODO [Etapa 3]:
 *   1. Adicione o atributo privado  consultorio  (int) — número do consultório.
 *   2. Receba-o no construtor e crie o getter getConsultorio().
 *   3. Implemente getIdentificacao() retornando:
 *        "Dr(a). <nome> — <registroConselho> (Consultório <n>)"
 *      Ex.: "Dr(a). Paulo Lima — CRM-DF 45678 (Consultório 2)"
 */
public class Medico extends Profissional {

    // TODO: atributo consultorio

    public Medico(String nome, String cpf, LocalDate dataNascimento, String crm, int consultorio) {
        super(nome, cpf, dataNascimento, crm);
        // TODO: guardar o consultório
    }

    public int getConsultorio() {
        // TODO: retornar o atributo
        throw new UnsupportedOperationException("TODO: Medico.getConsultorio()");
    }

    @Override
    public String getIdentificacao() {
        // TODO: implementar conforme o comentário da classe
        throw new UnsupportedOperationException("TODO: Medico.getIdentificacao()");
    }
}
