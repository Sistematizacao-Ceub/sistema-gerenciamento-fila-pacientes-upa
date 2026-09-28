package br.ceub.poo.upa.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Paciente da UPA.
 *
 * <p>CONCEITOS: herança ({@code extends Pessoa}), interface ({@code implements Comparable}),
 * composição (TEM uma {@link Triagem}), encapsulamento (o status só muda por métodos
 * que respeitam o fluxo: AGUARDANDO_TRIAGEM → AGUARDANDO_ATENDIMENTO → ATENDIDO).</p>
 *
 * TODO [Etapa 3]: implementar getIdentificacao(), getSenhaFormatada(),
 *                 registrarTriagem(), marcarComoAtendido() e getPrioridade().
 * TODO [Etapa 4]: implementar compareTo() — é ele que define a ORDEM da fila!
 */
public class Paciente extends Pessoa implements Comparable<Paciente> {

    private final int senha;
    private final LocalDateTime horarioChegada;
    private StatusPaciente status;
    private Triagem triagem; // null até o paciente ser triado

    public Paciente(String nome, String cpf, LocalDate dataNascimento,
                    int senha, LocalDateTime horarioChegada) {
        super(nome, cpf, dataNascimento);
        this.senha = senha;
        this.horarioChegada = horarioChegada;
        this.status = StatusPaciente.AGUARDANDO_TRIAGEM;
    }

    public int getSenha() { return senha; }
    public LocalDateTime getHorarioChegada() { return horarioChegada; }
    public StatusPaciente getStatus() { return status; }
    public Triagem getTriagem() { return triagem; }

    /**
     * Senha com prefixo "P" e 3 dígitos. Ex.: senha 7 → "P007"; senha 125 → "P125".
     * Dica: String.format("P%03d", senha)
     */
    public String getSenhaFormatada() {
        // TODO
        throw new UnsupportedOperationException("TODO: Paciente.getSenhaFormatada()");
    }

    /**
     * Associa a triagem ao paciente e muda o status para AGUARDANDO_ATENDIMENTO.
     * Regra: só pode ser chamado se o status atual for AGUARDANDO_TRIAGEM;
     *        caso contrário lance IllegalStateException("Paciente já foi triado").
     */
    public void registrarTriagem(Triagem triagem) {
        // TODO
        throw new UnsupportedOperationException("TODO: Paciente.registrarTriagem()");
    }

    /**
     * Muda o status para ATENDIDO.
     * Regra: só pode ser chamado se o status for AGUARDANDO_ATENDIMENTO;
     *        caso contrário lance IllegalStateException.
     */
    public void marcarComoAtendido() {
        // TODO
        throw new UnsupportedOperationException("TODO: Paciente.marcarComoAtendido()");
    }

    /** Prioridade da triagem, ou null se ainda não foi triado. */
    public Prioridade getPrioridade() {
        // TODO
        throw new UnsupportedOperationException("TODO: Paciente.getPrioridade()");
    }

    /** Formato: "Paciente <nome> (senha P007)". */
    @Override
    public String getIdentificacao() {
        // TODO
        throw new UnsupportedOperationException("TODO: Paciente.getIdentificacao()");
    }

    /**
     * Define a ORDEM NATURAL dos pacientes na fila de atendimento.
     *
     * Regras, nesta ordem:
     *   1º) Prioridade mais grave primeiro  → compare prioridade.ordinal() (VERMELHO=0 vem antes).
     *   2º) Empate de prioridade → quem CHEGOU antes vem primeiro (horarioChegada).
     *   3º) Empate de horário → menor senha primeiro.
     *
     * Contrato: retorna NEGATIVO se "this" deve vir ANTES de "outro", POSITIVO se
     * deve vir DEPOIS e ZERO se são equivalentes.
     * Dica: Integer.compare(a, b)  e  horarioChegada.compareTo(outro.horarioChegada)
     * Pré-condição: os dois pacientes já foram triados (getPrioridade() != null).
     */
    @Override
    public int compareTo(Paciente outro) {
        // TODO
        throw new UnsupportedOperationException("TODO: Paciente.compareTo()");
    }
}
