package br.ceub.poo.upa.observador;

import br.ceub.poo.upa.modelo.Atendimento;

/** Simula o painel eletrônico da sala de espera. JÁ IMPLEMENTADO. */
public class PainelChamada implements ObservadorChamada {

    @Override
    public void aoChamarPaciente(Atendimento atendimento) {
        String linha = "═".repeat(50);
        System.out.println();
        System.out.println("╔" + linha + "╗");
        System.out.printf("║ %-48s ║%n", "PAINEL DE CHAMADA");
        System.out.printf("║ %-48s ║%n", "SENHA " + atendimento.getPaciente().getSenhaFormatada()
                + "  →  CONSULTÓRIO " + atendimento.getMedico().getConsultorio());
        System.out.printf("║ %-48s ║%n", atendimento.getPaciente().getNome());
        System.out.println("╚" + linha + "╝");
    }
}
