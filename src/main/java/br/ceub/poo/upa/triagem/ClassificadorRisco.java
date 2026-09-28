package br.ceub.poo.upa.triagem;

import br.ceub.poo.upa.modelo.Prioridade;
import br.ceub.poo.upa.modelo.SinaisVitais;

/**
 * CONTRATO para qualquer algoritmo de classificação de risco.
 *
 * <p>CONCEITOS:</p>
 * <ul>
 *   <li><b>Interface</b>: define O QUE deve ser feito, não COMO.</li>
 *   <li><b>Padrão Strategy</b>: a UPA pode trocar o protocolo (Manchester, outro protocolo
 *       municipal, uma versão pediátrica...) sem alterar o {@code UpaService}.</li>
 *   <li><b>SOLID – DIP</b> (Inversão de Dependência): o serviço depende desta ABSTRAÇÃO,
 *       não de uma classe concreta.</li>
 *   <li><b>SOLID – OCP</b> (Aberto/Fechado): novo protocolo = nova classe, sem editar as existentes.</li>
 * </ul>
 *
 * <p>JÁ IMPLEMENTADA.</p>
 */
public interface ClassificadorRisco {

    /** Retorna a prioridade adequada aos sinais vitais informados. Nunca retorna null. */
    Prioridade classificar(SinaisVitais sinais);

    /** Nome do protocolo, exibido no menu. */
    String getNomeProtocolo();
}
