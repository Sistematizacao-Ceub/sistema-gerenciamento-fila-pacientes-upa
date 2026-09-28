package br.ceub.poo.upa.modelo;

import br.ceub.poo.upa.excecao.SinaisVitaisInvalidosException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * TESTES ESCRITOS PELO GRUPO — este arquivo tem 1 exemplo.
 * TODO [Etapa 8]: um teste para CADA regra de validação (FC, PAS, PAD, PAD >= PAS, SpO2, dor)
 * e um teste mostrando que valores válidos NÃO lançam exceção (assertDoesNotThrow).
 */
class SinaisVitaisTest {

    @Test
    void temperaturaImpossivelLancaExcecao() {
        assertThrows(SinaisVitaisInvalidosException.class,
                () -> new SinaisVitais(50.0, 80, 120, 80, 98, 0, true));
    }
}
