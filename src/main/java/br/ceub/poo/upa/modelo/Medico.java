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

    private final int consultorio;

    public Medico(String nome, String cpf, LocalDate dataNascimento, String crm, int consultorio) {
        super(nome, cpf, dataNascimento, crm);
        this.consultorio = consultorio;
    }

    public int getConsultorio() {
        return consultorio;
    }

    @Override
    public String getIdentificacao() {
        return String.format("Dr(a). %s - %s (Consultório %d)", getNome(), getRegistroConselho(), getConsultorio());
    }
}
