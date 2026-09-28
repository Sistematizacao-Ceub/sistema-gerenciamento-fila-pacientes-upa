package br.ceub.poo.upa.repositorio;

import br.ceub.poo.upa.modelo.Paciente;

import java.util.List;
import java.util.Optional;

/**
 * Abstração do ARMAZENAMENTO de pacientes (padrão Repository).
 *
 * <p>SOLID – DIP: o {@code UpaService} conhece apenas esta interface. Hoje os dados ficam
 * em memória; amanhã podem ir para um arquivo CSV ou banco de dados SEM mudar o serviço
 * (basta criar outra classe que implemente esta interface).</p>
 * <p>SOLID – ISP: interface pequena, só com o que o serviço realmente usa. JÁ IMPLEMENTADA.</p>
 */
public interface PacienteRepository {

    void salvar(Paciente paciente);

    /** Registro MAIS RECENTE com esse CPF (um mesmo paciente pode voltar outro dia). */
    Optional<Paciente> buscarPorCpf(String cpfNormalizado);

    /** Todos os pacientes em ordem CRESCENTE de senha. */
    List<Paciente> listarTodos();
}
