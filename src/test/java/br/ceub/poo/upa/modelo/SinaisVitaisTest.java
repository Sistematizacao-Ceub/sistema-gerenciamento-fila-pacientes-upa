package br.ceub.poo.upa.modelo;

import br.ceub.poo.upa.excecao.SinaisVitaisInvalidosException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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

    @Test
    void frequenciaCardiacaImpossivelLancaExcecao() {
        assertThrows(SinaisVitaisInvalidosException.class,
                () -> new SinaisVitais(36.0, 10, 120, 80, 98, 0, true));
    }

    @Test
    void pressaoSistolicaImpossivelLancaExcecao() {
        assertThrows(SinaisVitaisInvalidosException.class,
                () -> new SinaisVitais(36.0, 80, 310, 80, 98, 0, true));
    }

    @Test
    void pressaoDiastolicaImpossivelLancaExcecao() {
        assertThrows(SinaisVitaisInvalidosException.class,
                () -> new SinaisVitais(36.0, 80, 120, 10, 98, 0, true));
    }

    @Test
    void saturacaoO2ImpossivelLancaExcecao() {
        assertThrows(SinaisVitaisInvalidosException.class,
                () -> new SinaisVitais(36.0, 80, 120, 80, 110, 0, true));
    }

    @Test
    void dorImpossivelLancaExcecao() {
        assertThrows(SinaisVitaisInvalidosException.class,
                () -> new SinaisVitais(36.0, 80, 120, 80, 98, 11, true));
    }

    @Test
    void sinaisVitaisPossiveisNaoLancaExcecao() throws SinaisVitaisInvalidosException {
        assertDoesNotThrow(
                () -> new SinaisVitais(36.0, 80, 120, 80, 98, 0, true));
    }

    @Test
    void pressaoDiastolicaMaiorOuIgualSistolicaLancaExcecao() {
        assertThrows(SinaisVitaisInvalidosException.class,
                () -> new SinaisVitais(36.5, 80, 100, 120, 98, 0, true));
        assertThrows(SinaisVitaisInvalidosException.class,
                () -> new SinaisVitais(36.5, 80, 120, 120, 98, 0, true));
    }
}
