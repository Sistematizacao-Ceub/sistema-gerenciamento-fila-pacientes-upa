package br.ceub.poo.upa.servico;

import br.ceub.poo.upa.algoritmo.Busca;
import br.ceub.poo.upa.algoritmo.Ordenacao;
import br.ceub.poo.upa.estrutura.Fila;
import br.ceub.poo.upa.estrutura.FilaPrioridade;
import br.ceub.poo.upa.estrutura.Pilha;
import br.ceub.poo.upa.excecao.CpfInvalidoException;
import br.ceub.poo.upa.excecao.PacienteDuplicadoException;
import br.ceub.poo.upa.excecao.PacienteNaoEncontradoException;
import br.ceub.poo.upa.modelo.Atendimento;
import br.ceub.poo.upa.modelo.Enfermeiro;
import br.ceub.poo.upa.modelo.Medico;
import br.ceub.poo.upa.modelo.Paciente;
import br.ceub.poo.upa.modelo.Prioridade;
import br.ceub.poo.upa.modelo.SinaisVitais;
import br.ceub.poo.upa.modelo.StatusPaciente;
import br.ceub.poo.upa.modelo.Triagem;
import br.ceub.poo.upa.observador.ObservadorChamada;
import br.ceub.poo.upa.repositorio.PacienteRepository;
import br.ceub.poo.upa.triagem.ClassificadorRisco;
import br.ceub.poo.upa.util.GeradorSenha;
import br.ceub.poo.upa.util.ValidadorCpf;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * CORAÇÃO DO SISTEMA: regras de negócio do fluxo da UPA.
 *
 *   chegada ──► [Fila de TRIAGEM (FIFO)] ──► triagem ──► [Fila de ATENDIMENTO (prioridade)]
 *           ──► chamada pelo médico ──► [Pilha de HISTÓRICO]
 *
 * <p>SOLID – SRP: esta classe NÃO lê teclado nem imprime na tela (isso é do {@code MenuCli}).
 * Ela só aplica regras. Por isso pode ser testada com JUnit.</p>
 * <p>SOLID – DIP / Injeção de dependência: classificador, repositório e relógio chegam
 * PRONTOS pelo construtor. Nos testes passamos um {@code Clock.fixed(...)} para controlar o tempo.</p>
 */
public class UpaService {

    private final ClassificadorRisco classificador;
    private final PacienteRepository repositorio;
    private final Clock relogio;
    private final GeradorSenha geradorSenha = new GeradorSenha();

    private final Fila<Paciente> filaTriagem = new Fila<>();
    private final FilaPrioridade<Paciente> filaAtendimento = new FilaPrioridade<>();
    private final Pilha<Atendimento> historico = new Pilha<>();
    private final List<ObservadorChamada> observadores = new ArrayList<>();

    public UpaService(ClassificadorRisco classificador, PacienteRepository repositorio, Clock relogio) {
        this.classificador = classificador;
        this.repositorio = repositorio;
        this.relogio = relogio;
    }

    // ================================================================ RF01 — CADASTRO

    /**
     * Cadastra o paciente na chegada e o coloca na FILA DE TRIAGEM.
     *
     * TODO [Etapa 6]:
     *  1. Se !ValidadorCpf.isValido(cpf) → throw new CpfInvalidoException(cpf)
     *  2. cpfNormalizado = ValidadorCpf.normalizar(cpf)
     *  3. Se repositorio.buscarPorCpf(cpfNormalizado) encontrar paciente com status
     *     DIFERENTE de ATENDIDO → throw new PacienteDuplicadoException(cpfNormalizado)
     *  4. Crie o Paciente com geradorSenha.proxima() e LocalDateTime.now(relogio)
     *  5. repositorio.salvar(...) e filaTriagem.enfileirar(...)
     *  6. Retorne o paciente criado.
     */
    public Paciente cadastrarPaciente(String nome, String cpf, LocalDate dataNascimento)
            throws CpfInvalidoException, PacienteDuplicadoException {
        // TODO
        throw new UnsupportedOperationException("TODO: UpaService.cadastrarPaciente()");
    }

    // ================================================================ RF02 — TRIAGEM

    /** Próximo paciente a ser triado, SEM removê-lo (para a tela mostrar quem é). JÁ IMPLEMENTADO. */
    public Paciente proximoParaTriagem() {
        return filaTriagem.espiar(); // FilaVaziaException se não houver ninguém
    }

    /**
     * Realiza a triagem do PRÓXIMO da fila de triagem.
     *
     * TODO [Etapa 6]:
     *  1. prioridade = classificador.classificar(sinais)        ← POLIMORFISMO via interface
     *  2. paciente = filaTriagem.desenfileirar()
     *  3. triagem = new Triagem(enfermeiro, sinais, prioridade, queixa, LocalDateTime.now(relogio))
     *  4. paciente.registrarTriagem(triagem)
     *  5. filaAtendimento.enfileirar(paciente)
     *  6. return triagem
     *  ATENÇÃO: classifique ANTES de desenfileirar — se algo falhar, o paciente não "some" da fila.
     */
    public Triagem triarProximo(Enfermeiro enfermeiro, SinaisVitais sinais, String queixa) {
        // TODO
        throw new UnsupportedOperationException("TODO: UpaService.triarProximo()");
    }

    // ================================================================ RF03 — CHAMADA

    /**
     * Chama o paciente MAIS PRIORITÁRIO para o médico informado.
     *
     * TODO [Etapa 6]:
     *  1. paciente = filaAtendimento.desenfileirar()
     *  2. paciente.marcarComoAtendido()
     *  3. atendimento = new Atendimento(paciente, medico, LocalDateTime.now(relogio))
     *  4. historico.empilhar(atendimento)
     *  5. Avise TODOS os observadores: for (ObservadorChamada o : observadores) o.aoChamarPaciente(atendimento);
     *  6. return atendimento
     */
    public Atendimento chamarProximo(Medico medico) {
        // TODO
        throw new UnsupportedOperationException("TODO: UpaService.chamarProximo()");
    }

    public void adicionarObservador(ObservadorChamada observador) {
        observadores.add(observador);
    }

    // ================================================================ RF04 — CONSULTAS DE FILA

    public List<Paciente> listarFilaTriagem() {
        return filaTriagem.paraLista();
    }

    public List<Paciente> listarFilaAtendimento() {
        return filaAtendimento.paraLista();
    }

    // ================================================================ RF05 — BUSCAS

    /** Busca por CPF (sequencial, dentro do repositório). JÁ IMPLEMENTADO como modelo. */
    public Paciente buscarPorCpf(String cpf) throws PacienteNaoEncontradoException {
        String cpfNormalizado = ValidadorCpf.normalizar(cpf);
        return repositorio.buscarPorCpf(cpfNormalizado)
                .orElseThrow(() -> new PacienteNaoEncontradoException(cpf));
    }

    /**
     * Busca por número de senha usando BUSCA BINÁRIA.
     *
     * TODO [Etapa 6]:
     *  1. todos = repositorio.listarTodos()   (já vem ordenado por senha)
     *  2. indice = Busca.buscaBinaria(todos, senha, Paciente::getSenha)
     *  3. Se indice == -1 → throw new PacienteNaoEncontradoException("senha " + senha)
     *  4. return todos.get(indice)
     */
    public Paciente buscarPorSenha(int senha) throws PacienteNaoEncontradoException {
        // TODO
        throw new UnsupportedOperationException("TODO: UpaService.buscarPorSenha()");
    }

    // ================================================================ RF06 — RELATÓRIOS

    /** Últimos n atendimentos, do mais recente para o mais antigo (vem da PILHA). JÁ IMPLEMENTADO. */
    public List<Atendimento> ultimosAtendimentos(int n) {
        List<Atendimento> todos = historico.paraLista();
        return todos.subList(0, Math.min(n, todos.size()));
    }

    /**
     * Todos os atendimentos ordenados do MAIOR para o MENOR tempo de espera.
     *
     * TODO [Etapa 6]: use Ordenacao.mergeSort(historico.paraLista(), comparador) com
     *   Comparator.comparingLong(Atendimento::getTempoEsperaMin).reversed()
     */
    public List<Atendimento> relatorioPorTempoEspera() {
        // TODO
        throw new UnsupportedOperationException("TODO: UpaService.relatorioPorTempoEspera()");
    }

    // ================================================================ RF07 — ESTATÍSTICAS

    /**
     * Quantidade de pacientes JÁ TRIADOS por cor (inclui quem ainda espera e quem já foi atendido).
     * Todas as 5 cores devem aparecer no mapa, mesmo com zero.
     *
     * TODO [Etapa 6]: EnumMap<Prioridade,Integer>; inicialize as 5 cores com 0 (for sobre
     * Prioridade.values()) e percorra repositorio.listarTodos() somando quem tem triagem.
     */
    public Map<Prioridade, Integer> contarPorPrioridade() {
        Map<Prioridade, Integer> contagem = new EnumMap<>(Prioridade.class);
        // TODO
        throw new UnsupportedOperationException("TODO: UpaService.contarPorPrioridade()");
    }

    /**
     * Média do tempo de espera (min) dos atendimentos realizados; 0.0 se não houver nenhum.
     * TODO [Etapa 6]
     */
    public double tempoMedioEsperaMin() {
        // TODO
        throw new UnsupportedOperationException("TODO: UpaService.tempoMedioEsperaMin()");
    }

    public String getNomeProtocolo() {
        return classificador.getNomeProtocolo();
    }
}
