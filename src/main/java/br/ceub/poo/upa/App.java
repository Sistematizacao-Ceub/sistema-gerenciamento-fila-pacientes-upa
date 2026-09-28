package br.ceub.poo.upa;

import br.ceub.poo.upa.cli.Console;
import br.ceub.poo.upa.cli.MenuCli;
import br.ceub.poo.upa.modelo.Enfermeiro;
import br.ceub.poo.upa.modelo.Medico;
import br.ceub.poo.upa.observador.PainelChamada;
import br.ceub.poo.upa.repositorio.PacienteRepositoryMemoria;
import br.ceub.poo.upa.servico.UpaService;
import br.ceub.poo.upa.triagem.ClassificadorManchesterSimplificado;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Ponto de entrada. Aqui é a "RAIZ DE COMPOSIÇÃO": o ÚNICO lugar que conhece as
 * classes CONCRETAS e as conecta (injeção de dependência manual).
 *
 * TODO [Etapa 7]: troque os dados fictícios do plantão (nomes, registros) se quiser.
 */
public class App {

    public static void main(String[] args) {
        UpaService servico = new UpaService(
                new ClassificadorManchesterSimplificado(),  // Strategy
                new PacienteRepositoryMemoria(),            // Repository
                Clock.systemDefaultZone());                  // relógio real
        servico.adicionarObservador(new PainelChamada());   // Observer

        // Equipe de plantão (dados FICTÍCIOS; profissionais não passam pela validação de CPF)
        Enfermeiro enfermeiro = new Enfermeiro("Carla Mendes", "00000000000",
                LocalDate.of(1990, 3, 15), "COREN-DF 123456");
        List<Medico> medicos = List.of(
                new Medico("Paulo Lima", "00000000000", LocalDate.of(1982, 7, 2), "CRM-DF 45678", 1),
                new Medico("Ana Souza", "00000000000", LocalDate.of(1987, 11, 20), "CRM-DF 56789", 2));

        try (Scanner scanner = new Scanner(System.in)) {
            new MenuCli(servico, new Console(scanner), enfermeiro, medicos).iniciar();
        }
    }
}
