package br.ceub.poo.upa.cli;

import br.ceub.poo.upa.excecao.FilaVaziaException;
import br.ceub.poo.upa.excecao.UpaException;
import br.ceub.poo.upa.modelo.Atendimento;
import br.ceub.poo.upa.modelo.Enfermeiro;
import br.ceub.poo.upa.modelo.Medico;
import br.ceub.poo.upa.modelo.Paciente;
import br.ceub.poo.upa.modelo.Prioridade;
import br.ceub.poo.upa.modelo.SinaisVitais;
import br.ceub.poo.upa.modelo.Triagem;
import br.ceub.poo.upa.servico.UpaService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
        // TODO
        throw new UnsupportedOperationException("MenuCli.realizarTriagem()");
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
    private void chamarProximo() {
        System.out.println("\n--- CHAMAR PRÓXIMO PACIENTE ---");
        // TODO
        throw new UnsupportedOperationException("MenuCli.chamarProximo()");
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
        // TODO
        throw new UnsupportedOperationException("MenuCli.buscarPaciente()");
    }

    // ---------------------------------------------------------------- 6

    /**
     * TODO [Etapa 7]: pergunte "1-Últimos 5 atendimentos / 2-Ordenado por tempo de espera";
     * use servico.ultimosAtendimentos(5) ou servico.relatorioPorTempoEspera() e imprima cada
     * Atendimento (o toString() já está pronto).
     */
    private void exibirRelatorios() {
        System.out.println("\n--- RELATÓRIOS ---");
        // TODO
        throw new UnsupportedOperationException("MenuCli.exibirRelatorios()");
    }

    // ---------------------------------------------------------------- 7

    /**
     * TODO [Etapa 7]: imprima, para cada Prioridade, a quantidade (servico.contarPorPrioridade())
     * e uma "barra" de asteriscos proporcional (ex.: AMARELO  | *** 3), e o tempo médio de espera
     * com 1 casa decimal (servico.tempoMedioEsperaMin()).
     */
    private void exibirEstatisticas() {
        System.out.println("\n--- ESTATÍSTICAS DO PLANTÃO ---");
        // TODO
        throw new UnsupportedOperationException("MenuCli.exibirEstatisticas()");
    }
}
