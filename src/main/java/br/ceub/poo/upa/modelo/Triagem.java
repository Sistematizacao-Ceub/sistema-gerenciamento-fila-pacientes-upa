package br.ceub.poo.upa.modelo;

import java.time.LocalDateTime;

/**
 * Registro de uma triagem (classificação de risco).
 *
 * <p>CONCEITO: <b>composição</b>. Uma Triagem É COMPOSTA POR um {@link SinaisVitais},
 * um {@link Enfermeiro} e uma {@link Prioridade}. Relação "TEM-UM" (has-a),
 * diferente da herança, que é "É-UM" (is-a). Um Paciente, por sua vez, TEM uma Triagem.</p>
 *
 * <p>JÁ IMPLEMENTADA (imutável).</p>
 */
public class Triagem {

    private final Enfermeiro enfermeiro;
    private final SinaisVitais sinaisVitais;
    private final Prioridade prioridade;
    private final String queixaPrincipal;
    private final LocalDateTime horario;

    public Triagem(Enfermeiro enfermeiro, SinaisVitais sinaisVitais, Prioridade prioridade,
                   String queixaPrincipal, LocalDateTime horario) {
        this.enfermeiro = enfermeiro;
        this.sinaisVitais = sinaisVitais;
        this.prioridade = prioridade;
        this.queixaPrincipal = queixaPrincipal;
        this.horario = horario;
    }

    public Enfermeiro getEnfermeiro() { return enfermeiro; }
    public SinaisVitais getSinaisVitais() { return sinaisVitais; }
    public Prioridade getPrioridade() { return prioridade; }
    public String getQueixaPrincipal() { return queixaPrincipal; }
    public LocalDateTime getHorario() { return horario; }

    @Override
    public String toString() {
        return prioridade.name() + " | " + queixaPrincipal + " | " + sinaisVitais
                + " | por " + enfermeiro.getIdentificacao();
    }
}
