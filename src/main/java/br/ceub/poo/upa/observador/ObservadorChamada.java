package br.ceub.poo.upa.observador;

import br.ceub.poo.upa.modelo.Atendimento;

/**
 * PADRÃO OBSERVER: quem quiser ser AVISADO quando um paciente for chamado implementa
 * esta interface e se registra no serviço (painel da recepção, log, alerta sonoro...).
 * O serviço não precisa conhecer as classes concretas → baixo acoplamento. JÁ IMPLEMENTADA.
 */
public interface ObservadorChamada {

    void aoChamarPaciente(Atendimento atendimento);
}
