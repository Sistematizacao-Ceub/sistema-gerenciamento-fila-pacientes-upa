package br.ceub.poo.upa.modelo;

import br.ceub.poo.upa.excecao.SinaisVitaisInvalidosException;

/**
 * Sinais vitais aferidos na triagem.
 *
 * <p>CONCEITOS:</p>
 * <ul>
 *   <li><b>Objeto imutável</b>: todos os atributos são {@code private final} e NÃO há setters.
 *       Depois de criado, o objeto nunca muda → seguro para compartilhar.</li>
 *   <li><b>Encapsulamento com validação</b>: o construtor recusa valores impossíveis
 *       lançando uma exceção. Assim, é IMPOSSÍVEL existir um SinaisVitais inválido.</li>
 * </ul>
 *
 * TODO [Etapa 5]: complete o método validar() com TODAS as regras abaixo
 * (a de temperatura já está pronta como exemplo):
 *
 *   | Campo              | Faixa aceita                         |
 *   |--------------------|--------------------------------------|
 *   | temperatura        | 30.0 a 45.0 °C                       |
 *   | frequenciaCardiaca | 20 a 250 bpm                         |
 *   | pressaoSistolica   | 40 a 300 mmHg                        |
 *   | pressaoDiastolica  | 20 a 200 mmHg  E  menor que a sistólica |
 *   | saturacaoO2        | 50 a 100 %                           |
 *   | dor                | 0 a 10 (escala numérica de dor)      |
 */
public class SinaisVitais {

    private final double temperatura;
    private final int frequenciaCardiaca;
    private final int pressaoSistolica;
    private final int pressaoDiastolica;
    private final int saturacaoO2;
    private final int dor;
    private final boolean consciente;

    public SinaisVitais(double temperatura, int frequenciaCardiaca, int pressaoSistolica,
                        int pressaoDiastolica, int saturacaoO2, int dor, boolean consciente)
            throws SinaisVitaisInvalidosException {
        this.temperatura = temperatura;
        this.frequenciaCardiaca = frequenciaCardiaca;
        this.pressaoSistolica = pressaoSistolica;
        this.pressaoDiastolica = pressaoDiastolica;
        this.saturacaoO2 = saturacaoO2;
        this.dor = dor;
        this.consciente = consciente;
        validar();
    }

    private void validar() throws SinaisVitaisInvalidosException {
        if (temperatura < 30.0 || temperatura > 45.0) {
            throw new SinaisVitaisInvalidosException(
                    "Temperatura fora da faixa (30.0–45.0 °C): " + temperatura);
        }
        // TODO: validar frequenciaCardiaca
        // TODO: validar pressaoSistolica
        // TODO: validar pressaoDiastolica (faixa E menor que a sistólica)
        // TODO: validar saturacaoO2
        // TODO: validar dor
    }

    public double getTemperatura() { return temperatura; }
    public int getFrequenciaCardiaca() { return frequenciaCardiaca; }
    public int getPressaoSistolica() { return pressaoSistolica; }
    public int getPressaoDiastolica() { return pressaoDiastolica; }
    public int getSaturacaoO2() { return saturacaoO2; }
    public int getDor() { return dor; }
    public boolean isConsciente() { return consciente; }

    @Override
    public String toString() {
        return String.format("T=%.1f°C | FC=%d bpm | PA=%d/%d mmHg | SpO2=%d%% | Dor=%d/10 | %s",
                temperatura, frequenciaCardiaca, pressaoSistolica, pressaoDiastolica,
                saturacaoO2, dor, consciente ? "consciente" : "INCONSCIENTE");
    }
}
