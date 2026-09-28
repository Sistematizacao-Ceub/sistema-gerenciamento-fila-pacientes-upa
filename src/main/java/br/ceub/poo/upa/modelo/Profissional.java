package br.ceub.poo.upa.modelo;

import java.time.LocalDate;

/**
 * Profissional de saúde de plantão. Também é ABSTRATA: não existe "profissional
 * genérico" na UPA — existe Enfermeiro ou Médico.
 *
 * <p>CONCEITO: herança em DOIS níveis (Pessoa → Profissional → Medico/Enfermeiro).
 * Profissional NÃO implementa {@code getIdentificacao()}; a obrigação passa adiante
 * para as subclasses concretas.</p>
 *
 * <p>JÁ IMPLEMENTADA.</p>
 */
public abstract class Profissional extends Pessoa {

    private final String registroConselho; // ex.: "CRM-DF 12345" ou "COREN-DF 98765"

    protected Profissional(String nome, String cpf, LocalDate dataNascimento, String registroConselho) {
        super(nome, cpf, dataNascimento); // chama o construtor da superclasse
        this.registroConselho = registroConselho;
    }

    public String getRegistroConselho() {
        return registroConselho;
    }
}
