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
        if (frequenciaCardiaca < 20 || frequenciaCardiaca > 250) {
            throw new SinaisVitaisInvalidosException(
                    "Frequência Cardíaca fora da faixa permitida (20-250 bpm): " + frequenciaCardiaca);
        }
        if (pressaoSistolica < 40 || pressaoSistolica > 300) {
            throw new SinaisVitaisInvalidosException(
                    "Pressão Sistólica fora da faixa (40-300 mmHg): " + pressaoSistolica);
        }
        if (pressaoDiastolica < 20 || pressaoDiastolica > 200) {
            throw new SinaisVitaisInvalidosException(
                    "Pressão Diastólica fora da faixa (20-200 mmHg): " + pressaoDiastolica);
        }
        if (pressaoDiastolica >= pressaoSistolica) {
            throw new SinaisVitaisInvalidosException(
                    "Pressão Diastólica deve ser menor que a Sistólica: " + pressaoSistolica + "/" + pressaoDiastolica);
        }
        if (saturacaoO2 < 50 || saturacaoO2 > 100) {
            throw new SinaisVitaisInvalidosException(
                    "Saturação O2 fora da faixa (50-100 % ): " + saturacaoO2);
        }
        if (dor < 0 || dor > 10) {
            throw new SinaisVitaisInvalidosException(
                    "Dor fora da faixa (0-10 % ): " + dor);
        }
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
                saturacaoO2, dor, consciente ? "Consciente" : "Inconsciente");
    }
}
