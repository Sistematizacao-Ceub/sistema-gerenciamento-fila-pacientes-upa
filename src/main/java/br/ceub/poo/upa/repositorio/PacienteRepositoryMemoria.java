package br.ceub.poo.upa.repositorio;

import br.ceub.poo.upa.modelo.Paciente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementação em MEMÓRIA (os dados somem ao fechar o programa). JÁ IMPLEMENTADA.
 * Como as senhas são sequenciais e salvamos na ordem de chegada, a lista já fica
 * ordenada por senha — requisito da busca binária.
 */
public class PacienteRepositoryMemoria implements PacienteRepository {

    private final List<Paciente> pacientes = new ArrayList<>();

    @Override
    public void salvar(Paciente paciente) {
        pacientes.add(paciente);
    }

    @Override
    public Optional<Paciente> buscarPorCpf(String cpfNormalizado) {
        for (int i = pacientes.size() - 1; i >= 0; i--) { // do mais recente para o mais antigo
            if (pacientes.get(i).getCpf().equals(cpfNormalizado)) {
                return Optional.of(pacientes.get(i));
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Paciente> listarTodos() {
        return new ArrayList<>(pacientes); // cópia defensiva: ninguém altera a lista interna
    }
}
