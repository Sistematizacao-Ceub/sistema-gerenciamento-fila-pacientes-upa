package br.ceub.poo.upa.util;

/**
 * Validação de CPF pelos DÍGITOS VERIFICADORES (algoritmo oficial da Receita Federal).
 *
 * <p>Classe utilitária: construtor privado + métodos {@code static}.</p>
 */
public final class ValidadorCpf {

    private ValidadorCpf() {
    }

    /** Remove tudo o que não for dígito. "529.982.247-25" → "52998224725". JÁ IMPLEMENTADO. */
    public static String normalizar(String cpf) {
        return cpf == null ? "" : cpf.replaceAll("\\D", "");
    }

    /** "52998224725" → "529.982.247-25". JÁ IMPLEMENTADO. Pré-condição: 11 dígitos. */
    public static String formatar(String cpfNormalizado) {
        return cpfNormalizado.substring(0, 3) + "." + cpfNormalizado.substring(3, 6) + "."
                + cpfNormalizado.substring(6, 9) + "-" + cpfNormalizado.substring(9);
    }

    /**
     * TODO [Etapa 1]: implementar. Aceita CPF com ou sem pontuação.
     *
     * ALGORITMO:
     *  1. c = normalizar(cpf). Se c.length() != 11 → false.
     *  2. Se todos os dígitos forem iguais (ex.: "11111111111") → false.
     *     Dica: c.chars().distinct().count() == 1
     *  3. 1º dígito verificador:
     *       soma = Σ d[i] * (10 - i), para i = 0..8
     *       resto = soma % 11 ;  dv1 = (resto < 2) ? 0 : 11 - resto
     *  4. 2º dígito verificador:
     *       soma = Σ d[i] * (11 - i), para i = 0..9   (já incluindo o dv1)
     *       resto = soma % 11 ;  dv2 = (resto < 2) ? 0 : 11 - resto
     *  5. Válido se dv1 == d[9] E dv2 == d[10].
     *
     *  Dica: d[i] = Character.getNumericValue(c.charAt(i))  ou  c.charAt(i) - '0'
     *  Exemplo válido para testar: 529.982.247-25
     */
    public static boolean isValido(String cpf) {
        // TODO
        throw new UnsupportedOperationException("TODO: ValidadorCpf.isValido()");
    }
}
