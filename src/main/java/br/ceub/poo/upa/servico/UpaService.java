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

    // RF01 — CADASTRO

    public Paciente cadastrarPaciente(String nome, String cpf, LocalDate dataNascimento)
            throws CpfInvalidoException, PacienteDuplicadoException {

        if (!ValidadorCpf.isValido(cpf)) {
            throw new CpfInvalidoException(cpf);
        }

        String cpfNormalizado = ValidadorCpf.normalizar(cpf);

        Paciente existente = repositorio.buscarPorCpf(cpfNormalizado).orElse(null);

        if (existente != null && existente.getStatus() != StatusPaciente.ATENDIDO) {
            throw new PacienteDuplicadoException(cpfNormalizado);
        }

        Paciente paciente = new Paciente(
                nome,
                cpfNormalizado,
                dataNascimento,
                geradorSenha.proxima(),
                LocalDateTime.now(relogio)
        );

        repositorio.salvar(paciente);
        filaTriagem.enfileirar(paciente);

        return paciente;
    }

    // RF02 — TRIAGEM

    public Paciente proximoParaTriagem() {
        return filaTriagem.espiar();
    }

    public Triagem triarProximo(Enfermeiro enfermeiro, SinaisVitais sinais, String queixa) {

        Prioridade prioridade = classificador.classificar(sinais);

        Paciente paciente = filaTriagem.desenfileirar();

        Triagem triagem = new Triagem(
                enfermeiro,
                sinais,
                prioridade,
                queixa,
                LocalDateTime.now(relogio)
        );

        paciente.registrarTriagem(triagem);
        filaAtendimento.enfileirar(paciente);

        return triagem;
    }

    // RF03 — CHAMADA

    public Atendimento chamarProximo(Medico medico) {

        Paciente paciente = filaAtendimento.desenfileirar();

        paciente.marcarComoAtendido();

        Atendimento atendimento = new Atendimento(
                paciente,
                medico,
                LocalDateTime.now(relogio)
        );

        historico.empilhar(atendimento);

        for (ObservadorChamada observador : observadores) {
            observador.aoChamarPaciente(atendimento);
        }

        return atendimento;
    }

    public void adicionarObservador(ObservadorChamada observador) {
        observadores.add(observador);
    }

    // RF04 — CONSULTAS DE FILA

    public List<Paciente> listarFilaTriagem() {
        return filaTriagem.paraLista();
    }

    public List<Paciente> listarFilaAtendimento() {
        return filaAtendimento.paraLista();
    }

    // RF05 — BUSCAS

    public Paciente buscarPorCpf(String cpf) throws PacienteNaoEncontradoException {
        String cpfNormalizado = ValidadorCpf.normalizar(cpf);

        return repositorio.buscarPorCpf(cpfNormalizado)
                .orElseThrow(() -> new PacienteNaoEncontradoException(cpf));
    }

    public Paciente buscarPorSenha(int senha) throws PacienteNaoEncontradoException {

        List<Paciente> todos = repositorio.listarTodos();

        int indice = Busca.buscaBinaria(
                todos,
                senha,
                Paciente::getSenha
        );

        if (indice == -1) {
            throw new PacienteNaoEncontradoException("senha " + senha);
        }

        return todos.get(indice);
    }

    // RF06 — RELATÓRIOS

    public List<Atendimento> ultimosAtendimentos(int n) {
        List<Atendimento> todos = historico.paraLista();

        return todos.subList(0, Math.min(n, todos.size()));
    }

    public List<Atendimento> relatorioPorTempoEspera() {

        Comparator<Atendimento> comparador =
                Comparator.comparingLong(Atendimento::getTempoEsperaMin)
                        .reversed();

        return Ordenacao.mergeSort(historico.paraLista(), comparador);
    }

    // RF07 — ESTATÍSTICAS

    public Map<Prioridade, Integer> contarPorPrioridade() {

        Map<Prioridade, Integer> contagem =
                new EnumMap<>(Prioridade.class);

        for (Prioridade prioridade : Prioridade.values()) {
            contagem.put(prioridade, 0);
        }

        for (Paciente paciente : repositorio.listarTodos()) {

            if (paciente.getTriagem() != null) {
                Prioridade prioridade = paciente.getPrioridade();

                contagem.put(
                        prioridade,
                        contagem.get(prioridade) + 1
                );
            }
        }

        return contagem;
    }

    public double tempoMedioEsperaMin() {

        List<Atendimento> atendimentos = historico.paraLista();

        if (atendimentos.isEmpty()) {
            return 0.0;
        }

        double soma = 0.0;

        for (Atendimento atendimento : atendimentos) {
            soma += atendimento.getTempoEsperaMin();
        }

        return soma / atendimentos.size();
    }

    public String getNomeProtocolo() {
        return classificador.getNomeProtocolo();
    }
}