package br.ceub.poo.upa.modelo;

import java.time.LocalDate;

/**
 * Enfermeiro(a) responsável pela TRIAGEM (classificação de risco).
 *
 * TODO [Etapa 3]:
 *   1. Complete o construtor repassando os dados para a superclasse com super(...).
 *   2. Implemente getIdentificacao() retornando:  "Enf. <nome> — <registroConselho>"
 *      Ex.: "Enf. Carla Mendes — COREN-DF 123456"
 */
public class Enfermeiro extends Profissional {

    public Enfermeiro(String nome, String cpf, LocalDate dataNascimento, String coren) {
        super(nome, cpf, dataNascimento, coren); // (já feito para compilar — confira se entendeu!)
    }

    @Override
    public String getIdentificacao() {
        // TODO: implementar conforme o comentário da classe
        throw new UnsupportedOperationException("TODO: Enfermeiro.getIdentificacao()");
    }
}
