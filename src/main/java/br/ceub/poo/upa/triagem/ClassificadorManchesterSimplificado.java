package br.ceub.poo.upa.triagem;

import br.ceub.poo.upa.modelo.Prioridade;
import br.ceub.poo.upa.modelo.SinaisVitais;

/**
 * Implementação DIDÁTICA e simplificada do Protocolo de Manchester.
 * (Não usar em ambiente clínico real!)
 *
 * TODO [Etapa 4]: implementar classificar() com as regras abaixo.
 * As regras são avaliadas DA MAIS GRAVE PARA A MENOS GRAVE; a PRIMEIRA que for
 * verdadeira define a cor (basta UMA condição da linha para enquadrar).
 *
 *  VERMELHO: inconsciente  OU  SpO2 < 90  OU  FC < 40  OU  FC > 150  OU  PAS < 80
 *  LARANJA : SpO2 entre 90 e 93  OU  temperatura >= 40.0  OU  dor >= 8
 *            OU  FC > 130  OU  PAS >= 180
 *  AMARELO : temperatura >= 38.5  OU  dor >= 5  OU  FC > 110  OU  PAS >= 160
 *  VERDE   : temperatura >= 37.8  OU  dor >= 1
 *  AZUL    : nenhuma das anteriores
 *
 *  (PAS = pressão arterial sistólica; FC = frequência cardíaca)
 *
 * Operadores exigidos aqui: relacionais (<, >, >=) e lógicos (||, !).
 */
public class ClassificadorManchesterSimplificado implements ClassificadorRisco {

    @Override
    public Prioridade classificar(SinaisVitais sinais) {
        if (!sinais.isConsciente() || sinais.getSaturacaoO2() < 90 || sinais.getFrequenciaCardiaca() < 40
                || sinais.getFrequenciaCardiaca() > 150 || sinais.getPressaoSistolica() < 80) {
            return Prioridade.VERMELHO;
        }
        if (sinais.getSaturacaoO2() <= 93 || sinais.getTemperatura() >= 40.0 || sinais.getDor() >= 8
                || sinais.getFrequenciaCardiaca() > 130 || sinais.getPressaoSistolica() >= 180) {
            return Prioridade.LARANJA;
        }
        if (sinais.getTemperatura() >= 38.5 || sinais.getDor() >= 5 || sinais.getFrequenciaCardiaca() > 110
                || sinais.getPressaoSistolica() >= 160) {
            return Prioridade.AMARELO;
        }
        if (sinais.getTemperatura() >= 37.8 || sinais.getDor() >= 1) {
            return Prioridade.VERDE;
        }
        return Prioridade.AZUL;
    }

    @Override
    public String getNomeProtocolo() {
        return "Manchester (simplificado, didático)";
    }
}
