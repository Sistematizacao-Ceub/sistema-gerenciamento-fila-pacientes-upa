package br.ceub.poo.upa.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Testes FORNECIDOS pelo professor. Todos devem passar ao final. */
class ValidadorCpfTest {

    @Test
    @DisplayName("CPF válido com pontuação")
    void cpfValidoFormatado() {
        assertTrue(ValidadorCpf.isValido("529.982.247-25"));
    }

    @Test
    @DisplayName("CPF válido só com dígitos")
    void cpfValidoSemPontuacao() {
        assertTrue(ValidadorCpf.isValido("11144477735"));
    }

    @Test
    @DisplayName("Dígito verificador errado é inválido")
    void digitoVerificadorErrado() {
        assertFalse(ValidadorCpf.isValido("529.982.247-24"));
    }

    @Test
    @DisplayName("Todos os dígitos iguais é inválido (passaria no cálculo!)")
    void todosDigitosIguais() {
        assertFalse(ValidadorCpf.isValido("111.111.111-11"));
    }

    @Test
    @DisplayName("Tamanho diferente de 11 é inválido")
    void tamanhoErrado() {
        assertFalse(ValidadorCpf.isValido("1234567890"));
        assertFalse(ValidadorCpf.isValido(""));
        assertFalse(ValidadorCpf.isValido(null));
    }
}
