package br.ceub.poo.upa.servico;

import br.ceub.poo.upa.excecao.CpfInvalidoException;
import br.ceub.poo.upa.excecao.PacienteDuplicadoException;
import br.ceub.poo.upa.excecao.PacienteNaoEncontradoException;
import br.ceub.poo.upa.excecao.SinaisVitaisInvalidosException;
import br.ceub.poo.upa.modelo.Atendimento;
import br.ceub.poo.upa.modelo.Enfermeiro;
import br.ceub.poo.upa.modelo.Medico;
import br.ceub.poo.upa.modelo.Paciente;
import br.ceub.poo.upa.modelo.Prioridade;
import br.ceub.poo.upa.modelo.SinaisVitais;
import br.ceub.poo.upa.repositorio.PacienteRepositoryMemoria;
import br.ceub.poo.upa.triagem.ClassificadorManchesterSimplificado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Testes de INTEGRAÇÃO do fluxo completo. FORNECIDOS pelo professor.
 * Usam um relógio "de mentira" que só anda quando mandamos → testes determinísticos.
 */
class UpaServiceTest {

    /** Clock ajustável: começa às 08:00 e avança com avancar(minutos). */
    private static final class RelogioTeste extends Clock {
        private Instant agora = Instant.parse("2026-10-01T11:00:00Z"); // 08:00 em Brasília
        private final ZoneId zona = ZoneId.of("America/Sao_Paulo");

        void avancar(long minutos) { agora = agora.plus(Duration.ofMinutes(minutos)); }
        @Override public ZoneId getZone() { return zona; }
        @Override public Clock withZone(ZoneId zone) { return this; }
        @Override public Instant instant() { return agora; }
    }

    private RelogioTeste relogio;
    private UpaService servico;
    private final Enfermeiro enfermeiro = new Enfermeiro("Carla", "00000000000", LocalDate.of(1990, 1, 1), "COREN-DF 1");
    private final Medico medico = new Medico("Paulo", "00000000000", LocalDate.of(1980, 1, 1), "CRM-DF 1", 1);
    private final LocalDate nascimento = LocalDate.of(2000, 5, 10);

    @BeforeEach
    void preparar() {
        relogio = new RelogioTeste();
        servico = new UpaService(new ClassificadorManchesterSimplificado(), new PacienteRepositoryMemoria(), relogio);
    }

    private SinaisVitais normais() throws SinaisVitaisInvalidosException {
        return new SinaisVitais(36.5, 80, 120, 80, 98, 0, true);            // AZUL
    }

    private SinaisVitais graves() throws SinaisVitaisInvalidosException {
        return new SinaisVitais(36.5, 80, 120, 80, 85, 0, true);            // VERMELHO (SpO2 85)
    }

    @Test
    void cadastroGeraSenhasSequenciaisEEntraNaFilaDeTriagem() throws Exception {
        Paciente a = servico.cadastrarPaciente("Ana", "529.982.247-25", nascimento);
        Paciente b = servico.cadastrarPaciente("Bruno", "111.444.777-35", nascimento);
        assertEquals("P001", a.getSenhaFormatada());
        assertEquals("P002", b.getSenhaFormatada());
        assertEquals(2, servico.listarFilaTriagem().size());
    }

    @Test
    void cpfInvalidoOuDuplicadoEhRecusado() throws Exception {
        assertThrows(CpfInvalidoException.class,
                () -> servico.cadastrarPaciente("X", "123.456.789-00", nascimento));
        servico.cadastrarPaciente("Ana", "529.982.247-25", nascimento);
        assertThrows(PacienteDuplicadoException.class,
                () -> servico.cadastrarPaciente("Ana de novo", "52998224725", nascimento));
    }

    @Test
    void pacienteGraveQueChegouDepoisPassaNaFrente() throws Exception {
        servico.cadastrarPaciente("Ana", "529.982.247-25", nascimento);
        relogio.avancar(5);
        servico.cadastrarPaciente("Bruno", "111.444.777-35", nascimento);

        servico.triarProximo(enfermeiro, normais(), "dor de garganta"); // Ana → AZUL
        servico.triarProximo(enfermeiro, graves(), "falta de ar");      // Bruno → VERMELHO

        List<Paciente> fila = servico.listarFilaAtendimento();
        assertEquals("Bruno", fila.get(0).getNome());
        assertEquals(Prioridade.VERMELHO, fila.get(0).getPrioridade());
    }

    @Test
    void mesmaCorAtendeQuemChegouPrimeiro() throws Exception {
        servico.cadastrarPaciente("Ana", "529.982.247-25", nascimento);
        relogio.avancar(1);
        servico.cadastrarPaciente("Bruno", "111.444.777-35", nascimento);
        servico.triarProximo(enfermeiro, normais(), "tosse");
        servico.triarProximo(enfermeiro, normais(), "tosse");
        assertEquals("Ana", servico.chamarProximo(medico).getPaciente().getNome());
    }

    @Test
    void chamadaCalculaEsperaEAlimentaHistoricoERelatorio() throws Exception {
        servico.cadastrarPaciente("Ana", "529.982.247-25", nascimento);   // 08:00
        relogio.avancar(10);
        servico.cadastrarPaciente("Bruno", "111.444.777-35", nascimento); // 08:10
        servico.triarProximo(enfermeiro, normais(), "tosse");
        servico.triarProximo(enfermeiro, normais(), "tosse");
        relogio.avancar(30);                                               // 08:40
        Atendimento primeiro = servico.chamarProximo(medico);              // Ana: 40 min
        relogio.avancar(20);                                               // 09:00
        servico.chamarProximo(medico);                                     // Bruno: 50 min

        assertEquals(40, primeiro.getTempoEsperaMin());
        assertEquals("Bruno", servico.ultimosAtendimentos(5).get(0).getPaciente().getNome()); // pilha
        assertEquals("Bruno", servico.relatorioPorTempoEspera().get(0).getPaciente().getNome()); // maior espera
        assertEquals(45.0, servico.tempoMedioEsperaMin(), 0.001);
        assertEquals(2, (int) servico.contarPorPrioridade().get(Prioridade.AZUL));
        assertEquals(0, (int) servico.contarPorPrioridade().get(Prioridade.VERMELHO));
    }

    @Test
    void buscaPorSenha() throws Exception {
        servico.cadastrarPaciente("Ana", "529.982.247-25", nascimento);
        servico.cadastrarPaciente("Bruno", "111.444.777-35", nascimento);
        servico.cadastrarPaciente("Caio", "123.456.789-09", nascimento);
        assertEquals("Caio", servico.buscarPorSenha(3).getNome());
        assertThrows(PacienteNaoEncontradoException.class, () -> servico.buscarPorSenha(99));
    }
}
