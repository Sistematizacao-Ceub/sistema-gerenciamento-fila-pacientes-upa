package br.ceub.poo.upa.cli;

import br.ceub.poo.upa.excecao.CpfInvalidoException;
import br.ceub.poo.upa.excecao.FilaVaziaException;
import br.ceub.poo.upa.excecao.MedicoNaoInformadoException;
import br.ceub.poo.upa.excecao.UpaException;
import br.ceub.poo.upa.modelo.Atendimento;
import br.ceub.poo.upa.modelo.Enfermeiro;
import br.ceub.poo.upa.modelo.Medico;
import br.ceub.poo.upa.modelo.Paciente;
import br.ceub.poo.upa.modelo.SinaisVitais;
import br.ceub.poo.upa.modelo.Triagem;
import br.ceub.poo.upa.servico.UpaService;
import br.ceub.poo.upa.util.ValidadorCpf;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface de linha de comando (CLI). Responsabilidade ÚNICA: conversar com o usuário
 * (ler e mostrar). Toda regra de negócio é delegada ao {@link UpaService}.
 *
 * <p>O laço principal já trata as exceções de forma centralizada:</p>
 * <ul>
 *   <li>{@link UpaException} → erro de negócio, mostrado ao usuário;</li>
 *   <li>{@link FilaVaziaException} → fila vazia;</li>
 *   <li>{@link UnsupportedOperationException} → TODO ainda não implementado.</li>
 * </ul>
 */
public class MenuCli {

    private final UpaService servico;
    private final Console console;
    private final Enfermeiro enfermeiroPlantao;
    private final List<Medico> medicosPlantao;

    public MenuCli(UpaService servico, Console console, Enfermeiro enfermeiroPlantao, List<Medico> medicosPlantao) {
        this.servico = servico;
        this.console = console;
        this.enfermeiroPlantao = enfermeiroPlantao;
        this.medicosPlantao = medicosPlantao;
    }

    public void iniciar() {
        int opcao;
        do {
            exibirMenu();
            opcao = console.lerInteiro("Opção", 0, 7);
            try {
                switch (opcao) {
                    case 1 -> cadastrarPaciente();
                    case 2 -> realizarTriagem();
                    case 3 -> chamarProximo();
                    case 4 -> exibirFilas();
                    case 5 -> buscarPaciente();
                    case 6 -> exibirRelatorios();
                    case 7 -> exibirEstatisticas();
                    case 0 -> System.out.println("\nEncerrando o sistema. Bom plantão!");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (UpaException e) {
                System.out.println("\n  [ERRO] " + e.getMessage());
            } catch (FilaVaziaException e) {
                System.out.println("\n  [INFO] " + e.getMessage());
            } catch (UnsupportedOperationException e) {
                System.out.println("\n  [TODO] Ainda não implementado -> " + e.getMessage());
            }
            if (opcao != 0) {
                console.pausar();
            }
        } while (opcao != 0);
    }

    private void exibirMenu() {
        System.out.println();
        System.out.println("=================================================");
        System.out.println("   UPA 24h — GESTÃO DE FILA DE PACIENTES");
        System.out.println("   Protocolo: " + servico.getNomeProtocolo());
        System.out.println("=================================================");
        System.out.println("  1. Cadastrar paciente (recepção)");
        System.out.println("  2. Realizar triagem do próximo paciente");
        System.out.println("  3. Chamar próximo paciente (médico)");
        System.out.println("  4. Visualizar filas");
        System.out.println("  5. Buscar paciente (CPF ou senha)");
        System.out.println("  6. Relatórios de atendimento");
        System.out.println("  7. Estatísticas do plantão");
        System.out.println("  0. Sair");
        System.out.println("-------------------------------------------------");
    }

    // ---------------------------------------------------------------- 1 (JÁ IMPLEMENTADO: modelo)

    private void cadastrarPaciente() throws UpaException {
        System.out.println("\n--- CADASTRO DE PACIENTE ---");
        String nome = console.lerTexto("Nome completo");
        String cpf = console.lerTexto("CPF");
        LocalDate nascimento = console.lerData("Data de nascimento");

        Paciente paciente = servico.cadastrarPaciente(nome, cpf, nascimento);

        System.out.println("\n  [OK] Paciente cadastrado!");
        System.out.println("    Senha: " + paciente.getSenhaFormatada()
                + " | Chegada: " + paciente.getHorarioChegada().format(Console.FORMATO_HORA));
        System.out.println("    Posição na fila de triagem: " + servico.listarFilaTriagem().size());
    }

    // ---------------------------------------------------------------- 2

    /**
     * TODO [Etapa 7]:
     *  1. Mostre quem é o próximo: servico.proximoParaTriagem().getIdentificacao()
     *  2. Leia a queixa principal (lerTexto)
     *  3. Leia os sinais vitais: temperatura (lerDecimal), FC, PA sistólica, PA diastólica,
     *     SpO2 e dor (lerInteiro com faixas coerentes) e consciente (lerSimNao)
     *  4. new SinaisVitais(...)  → pode lançar SinaisVitaisInvalidosException (é UpaException:
     *     o laço de iniciar() já trata; o paciente continua na fila)
     *  5. Triagem t = servico.triarProximo(enfermeiroPlantao, sinais, queixa)
     *  6. Mostre a cor e o tempo máximo de espera: t.getPrioridade()
     */
    private void realizarTriagem() throws UpaException {
        System.out.println("\n--- TRIAGEM (" + enfermeiroPlantao.getIdentificacao() + ") ---");
        System.out.println(" Próximo: " + servico.proximoParaTriagem().getIdentificacao());
        String queixa = console.lerTexto("Queixa principal");
        double temperatura = console.lerDecimal("Temperatura [30.0–45.0 °C]");
        int frequenciaCardiaca = console.lerInteiro("Frequência Cardíaca", 20, 250);
        int pressaoSistolica = console.lerInteiro("PA Sistólica", 40, 300);
        int pressaoDiastolica = console.lerInteiro("PA Diastólica", 20, 200);
        int saturacaoO2 = console.lerInteiro("Saturação O2 (%)", 50, 100);
        int dor = console.lerInteiro("Dor", 0, 10);
        boolean consciente = console.lerSimNao("Consciente?");
        SinaisVitais sinaisVitais = new SinaisVitais(temperatura, frequenciaCardiaca, pressaoSistolica, pressaoDiastolica, saturacaoO2, dor, consciente);
        Triagem triagem = servico.triarProximo(enfermeiroPlantao, sinaisVitais, queixa);
        System.out.println("\n[OK] Classificação: " + triagem.getPrioridade());
        exibirEstatisticas();
    }

    // ---------------------------------------------------------------- 3

    /**
     * TODO [Etapa 7]:
     *  1. Liste os médicos de plantão numerados (1..n) com getIdentificacao()
     *  2. Leia a escolha com lerInteiro(…, 1, medicosPlantao.size())
     *  3. Atendimento a = servico.chamarProximo(medicoEscolhido)
     *     (o PainelChamada — observador — imprime o painel sozinho!)
     *  4. Mostre o tempo de espera e se foi dentro do prazo da cor.
     */
    private void chamarProximo() throws MedicoNaoInformadoException {
        System.out.println("\n--- CHAMAR PRÓXIMO PACIENTE ---");
        for (int i = 0; i < medicosPlantao.size(); i++) {
            System.out.printf("%d - %s%n", i + 1, medicosPlantao.get(i).getIdentificacao());
        }
        int escolha = console.lerInteiro("Escolha um médico", 1, medicosPlantao.size());
        Atendimento atendimento = servico.chamarProximo(medicosPlantao.get(escolha - 1));
        System.out.printf("%nTempo de espera: %d min | %s%n", atendimento.getTempoEsperaMin(), atendimento.isDentroDoPrazo() ? "Dentro do prazo da cor." : "Fora do prazo da cor.");
    }

    // ---------------------------------------------------------------- 4 (JÁ IMPLEMENTADO: modelo)

    private void exibirFilas() {
        List<Paciente> triagem = servico.listarFilaTriagem();
        System.out.println("\n--- FILA DE TRIAGEM (" + triagem.size() + ") — ordem de chegada ---");
        if (triagem.isEmpty()) {
            System.out.println("  (vazia)");
        }
        for (int i = 0; i < triagem.size(); i++) {
            Paciente p = triagem.get(i);
            System.out.printf("  %2d. %s | %-25s | chegou %s%n", i + 1, p.getSenhaFormatada(),
                    p.getNome(), p.getHorarioChegada().format(Console.FORMATO_HORA));
        }

        List<Paciente> atendimento = servico.listarFilaAtendimento();
        System.out.println("\n--- FILA DE ATENDIMENTO (" + atendimento.size() + ") — por prioridade ---");
        if (atendimento.isEmpty()) {
            System.out.println("  (vazia)");
        }
        for (int i = 0; i < atendimento.size(); i++) {
            Paciente p = atendimento.get(i);
            System.out.printf("  %2d. [%-8s] %s | %-25s | chegou %s%n", i + 1, p.getPrioridade().name(),
                    p.getSenhaFormatada(), p.getNome(), p.getHorarioChegada().format(Console.FORMATO_HORA));
        }
    }

    // ---------------------------------------------------------------- 5

    /**
     * TODO [Etapa 7]: pergunte "1-CPF / 2-Senha"; chame servico.buscarPorCpf(...) ou
     * servico.buscarPorSenha(...) e exiba: identificação, idade, status e, se houver, a triagem.
     */
    private void buscarPaciente() throws UpaException {
        System.out.println("\n--- BUSCAR PACIENTE ---");
        int filtro = console.lerInteiro("Buscar por (1-CPF / 2-Senha)", 1, 2);
        if (filtro == 1) {
            String cpf = console.lerTexto("Digite o CPF do paciente [XXX.XXX.XXX-XX]");
            if(!ValidadorCpf.isValido(cpf)) throw new CpfInvalidoException(cpf);
            Paciente paciente = servico.buscarPorCpf(cpf);
            System.out.printf("%n%s, %d, %s, %s%n",
                    paciente.getIdentificacao(),
                    paciente.getIdade(),
                    paciente.getStatus().name(),
                    paciente.getTriagem() != null ? paciente.getTriagem() : "Não passou pela triagem" );
        } else {
            String senha = console.lerTexto("Digite a senha do paciente [ex.: P007 ou 007]")
                    .trim()
                    .toUpperCase();
            if (senha.startsWith("P")) {
                senha = senha.substring(1);
            }
            if (!senha.matches("\\d+")) {
                System.out.println("[ERRO] Senha inválida.");
                return;
            }
            Paciente paciente = servico.buscarPorSenha(Integer.parseInt(senha));
            System.out.printf("%n%s, %d, %s, %s%n",
                    paciente.getIdentificacao(),
                    paciente.getIdade(),
                    paciente.getStatus().name(),
                    paciente.getTriagem() != null ? paciente.getTriagem() : "Não passou pela triagem" );
        }
    }

    // ---------------------------------------------------------------- 6

    /**
     * TODO [Etapa 7]: pergunte "1-Últimos 5 atendimentos / 2-Ordenado por tempo de espera";
     * use servico.ultimosAtendimentos(5) ou servico.relatorioPorTempoEspera() e imprima cada
     * Atendimento (o toString() já está pronto).
     */
    private void exibirRelatorios() {
        System.out.println("\n--- RELATÓRIOS ---");
        int filtro = console.lerInteiro("Exibir relatório (1-Últimos 5 atendimentos / 2-Ordenado por tempo de espera)", 1, 2);
        List<Atendimento> atendimentos = filtro == 1 ?
                servico.ultimosAtendimentos(5) :
                servico.relatorioPorTempoEspera();
        if (atendimentos.isEmpty()) {
            System.out.println("Nenhum atendimento encontrado.");
            return;
        }
        atendimentos.forEach(System.out::println);
    }

    // ---------------------------------------------------------------- 7

    /**
     * TODO [Etapa 7]: imprima, para cada Prioridade, a quantidade (servico.contarPorPrioridade())
     * e uma "barra" de asteriscos proporcional (ex.: AMARELO  | *** 3), e o tempo médio de espera
     * com 1 casa decimal (servico.tempoMedioEsperaMin()).
     */
    private void exibirEstatisticas() {
        System.out.println("\n--- ESTATÍSTICAS DO PLANTÃO ---");
        servico.contarPorPrioridade().forEach((prioridade, quantidade) -> {
            System.out.printf("%-8s | %-10s %d%n", prioridade.name(), quantidade > 10 ? "+" : "*".repeat(quantidade), quantidade);
        });
        System.out.printf("Tempo médio de espera: %.1f min%n", servico.tempoMedioEsperaMin());
    }
}
