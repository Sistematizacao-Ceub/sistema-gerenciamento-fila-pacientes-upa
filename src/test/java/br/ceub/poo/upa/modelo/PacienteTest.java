package br.ceub.poo.upa.modelo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TESTES ESCRITOS PELO GRUPO — este arquivo tem 1 exemplo.
 *
 * TODO [Etapa 8]: escreva aqui E em SinaisVitaisTest pelo menos 8 testes NOVOS no total.
 * Sugestões:
 *   - compareTo: VERMELHO vem antes de AZUL; mesma cor → quem chegou antes
 *   - registrarTriagem duas vezes lança IllegalStateException
 *   - marcarComoAtendido sem triagem lança IllegalStateException
 *   - getIdentificacao() no formato pedido
 *   - Medico/Enfermeiro: getIdentificacao() (polimorfismo: mesma chamada, textos diferentes)
 *   - Atendimento.isDentroDoPrazo() true e false
 */
class PacienteTest {

    @Test
    void senhaFormatadaTemTresDigitos() {
        Paciente p = new Paciente("Ana", "52998224725", LocalDate.of(2000, 1, 1), 7, LocalDateTime.now());
        assertEquals("P007", p.getSenhaFormatada());
    }
}
