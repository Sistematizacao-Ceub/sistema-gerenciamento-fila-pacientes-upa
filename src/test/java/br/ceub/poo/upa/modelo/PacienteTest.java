package br.ceub.poo.upa.modelo;

import br.ceub.poo.upa.excecao.SinaisVitaisInvalidosException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TESTES ESCRITOS PELO GRUPO — este arquivo tem 1 exemplo.
 *
 * TODO [Etapa 8]: escreva aqui E em SinaisVitaisTest pelo menos 8 testes NOVOS no total.
 * Sugestões:
 *   - compareTo: VERMELHO vem antes de AZUL; mesma cor → quem chegou antes
 *   - registrarTriagem duas vezes lança IllegalStateException
 *   - marcarComoAtendido sem triagem lança IllegalStateException
 *   - getIdentificacao() no formato pedido
 *   - Medico/Enfermeiro: getIdentificacao() (polimorfismo: mesma chamada, textos diferentes)
 *   - Atendimento.isDentroDoPrazo() true e false
 */
class PacienteTest {

    private final LocalDate nascimento = LocalDate.parse("1991-09-28");
    private final LocalDateTime horarioChegada = LocalDateTime.parse("2026-11-01T08:00:00");

    private final Enfermeiro enfermeiro = new Enfermeiro(
            "Carla",
            "858.020.170-58",
            nascimento,
            "COREN-DF 1");

    private final Medico medico = new Medico(
            "Paulo",
            "464.265.770-30",
            nascimento.plusYears(10).plusMonths(1).minusDays(10),
            "CRM-DF 1",
            1);

    private SinaisVitais azul() throws SinaisVitaisInvalidosException {
        return new SinaisVitais(36.5, 80, 120, 80, 98, 0, true);
    }

    private SinaisVitais vermelho() throws SinaisVitaisInvalidosException {
        return new SinaisVitais(36.5, 80, 120, 80, 85, 0, true);
    }

    @Test
    void senhaFormatadaTemTresDigitos() {
        Paciente p = new Paciente("Ana", "332.857.350-07", LocalDate.of(2000, 1, 1), 7, LocalDateTime.now());
        assertEquals("P007", p.getSenhaFormatada());
    }

    @Test
    void vermelhoVemAntesDeAzulMesmoChegandoDepois() throws SinaisVitaisInvalidosException {
        Paciente pacienteAzul = criarPacienteTriado(Prioridade.AZUL, 1, horarioChegada);
        Paciente pacienteVermelho = criarPacienteTriado(Prioridade.VERMELHO, 2, horarioChegada.plusMinutes(10));
        int resultado = pacienteVermelho.compareTo(pacienteAzul);
        assertTrue(resultado < 0);
    }

    @Test
    void registrarTriagemDuasVezesLancaIllegalStateException() throws SinaisVitaisInvalidosException {
        Paciente pacienteTriado = criarPacienteTriado(Prioridade.AMARELO, 1, horarioChegada);
        assertThrows(IllegalStateException.class, () -> pacienteTriado.registrarTriagem(criarTriagem(Prioridade.VERMELHO, horarioChegada.plusHours(1))));
    }

    @Test
    void marcarComoAtendidoSemTriagemLancaIllegalStateException() {
        Paciente pacienteSemTriagem = criarPaciente(1, horarioChegada);
        assertThrows(IllegalStateException.class, pacienteSemTriagem::marcarComoAtendido);
    }

    @Test
    void getIdentificacaoNoFormatoPedido() {
        Paciente paciente = criarPaciente(8, horarioChegada);
        assertEquals("Paciente Paciente teste (senha P008)", paciente.getIdentificacao());
    }

    @Test
    void polimorfismoGetIdentificacaoMedicoEEnfermeiro() {
        Profissional enfermeiro = this.enfermeiro;
        Profissional medico = this.medico;
        String identificacaoEnfermeiro = enfermeiro.getIdentificacao();
        String identificacaoMedico = medico.getIdentificacao();
        assertNotEquals(identificacaoEnfermeiro, identificacaoMedico);
        assertEquals("Enf. Carla - COREN-DF 1", identificacaoEnfermeiro);
        assertEquals("Dr(a). Paulo - CRM-DF 1 (Consultório 1)", identificacaoMedico);
    }

    @Test
    void atendimentoDentroDoPrazoRetornaTrue() throws SinaisVitaisInvalidosException {
        Paciente pacienteAzul = criarPacienteTriado(Prioridade.AZUL, 1, horarioChegada);
        Atendimento atendimentoAzul = criarAtendimento(pacienteAzul, pacienteAzul.getTriagem().getHorario().plusMinutes(30));
        Paciente pacienteVermelho = criarPaciente( 1, horarioChegada);
        pacienteVermelho.registrarTriagem(criarTriagem(Prioridade.VERMELHO, horarioChegada));
        Atendimento atendimentoVermelho = criarAtendimento(pacienteVermelho, pacienteVermelho.getTriagem().getHorario());
        assertTrue(atendimentoVermelho.isDentroDoPrazo());
        assertTrue(atendimentoAzul.isDentroDoPrazo());
    }


    @Test
    void atendimentoForaDoPrazoRetornaFalse() throws SinaisVitaisInvalidosException {
        Paciente pacienteVermelho = criarPacienteTriado(Prioridade.VERMELHO, 1, horarioChegada);
        Atendimento atendimentoVermelho = criarAtendimento(pacienteVermelho, pacienteVermelho.getTriagem().getHorario());
        assertFalse(atendimentoVermelho.isDentroDoPrazo());
    }

    private Paciente criarPacienteTriado(Prioridade prioridade, int senha, LocalDateTime horarioChegada) throws SinaisVitaisInvalidosException {
        Paciente paciente = criarPaciente(senha, horarioChegada);
        paciente.registrarTriagem(criarTriagem(prioridade, horarioChegada.plusHours(1)));
        return paciente;
    }
    private Paciente criarPaciente(int senha, LocalDateTime horarioChegada) {
        return new Paciente("Paciente teste", "858.020.170-58", nascimento, senha, horarioChegada);
    }

    private Triagem criarTriagem(Prioridade prioridade, LocalDateTime horarioTriagem) throws SinaisVitaisInvalidosException {
        return new Triagem(
                enfermeiro,
                Prioridade.AZUL.equals(prioridade)
                        ? azul()
                        : vermelho(),
                prioridade,
                "Dor no corpo",
                horarioTriagem);
    }
    private Atendimento criarAtendimento(Paciente paciente, LocalDateTime horarioChamada) {
        return new Atendimento(paciente, this.medico, horarioChamada);
    }
}
