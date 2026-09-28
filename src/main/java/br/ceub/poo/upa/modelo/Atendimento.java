package br.ceub.poo.upa.modelo;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Registro da chamada de um paciente por um médico.
 * Composição: um Atendimento TEM um Paciente e um Medico.
 *
 * TODO [Etapa 3]: implementar getTempoEsperaMin() e isDentroDoPrazo().
 */
public class Atendimento {

    private final Paciente paciente;
    private final Medico medico;
    private final LocalDateTime horarioChamada;

    public Atendimento(Paciente paciente, Medico medico, LocalDateTime horarioChamada) {
        this.paciente = paciente;
        this.medico = medico;
        this.horarioChamada = horarioChamada;
    }

    public Paciente getPaciente() { return paciente; }
    public Medico getMedico() { return medico; }
    public LocalDateTime getHorarioChamada() { return horarioChamada; }

    /**
     * Minutos entre a CHEGADA do paciente e a CHAMADA pelo médico.
     * Dica: Duration.between(inicio, fim).toMinutes()
     */
    public long getTempoEsperaMin() {
        // TODO
        throw new UnsupportedOperationException("TODO: Atendimento.getTempoEsperaMin()");
    }

    /** true se o tempo de espera NÃO ultrapassou o tempo máximo da prioridade do paciente. */
    public boolean isDentroDoPrazo() {
        // TODO
        throw new UnsupportedOperationException("TODO: Atendimento.isDentroDoPrazo()");
    }

    @Override
    public String toString() {
        return String.format("%s | %s | espera: %d min | %s",
                paciente.getIdentificacao(), paciente.getPrioridade().name(),
                getTempoEsperaMin(), medico.getIdentificacao());
    }
}
