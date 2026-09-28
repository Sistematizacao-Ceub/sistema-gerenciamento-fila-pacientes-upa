package br.ceub.poo.upa.modelo;

/**
 * Classificação de risco baseada (de forma SIMPLIFICADA e didática) no
 * Protocolo de Manchester, utilizado em UPAs e prontos-socorros do Brasil.
 *
 * <p>CONCEITO: enum com atributos, construtor e métodos. Um enum em Java é uma
 * classe com um número FIXO de instâncias. A ORDEM de declaração importa:
 * {@code VERMELHO.ordinal() == 0} é a MAIS grave — usaremos isso para ordenar a fila.</p>
 *
 * <p>JÁ IMPLEMENTADO — não altere a ordem das constantes.</p>
 */
public enum Prioridade {

    VERMELHO("Emergência", 0),
    LARANJA("Muito urgente", 10),
    AMARELO("Urgente", 60),
    VERDE("Pouco urgente", 120),
    AZUL("Não urgente", 240);

    private final String descricao;
    private final int tempoMaximoEsperaMin;

    Prioridade(String descricao, int tempoMaximoEsperaMin) {
        this.descricao = descricao;
        this.tempoMaximoEsperaMin = tempoMaximoEsperaMin;
    }

    public String getDescricao() {
        return descricao;
    }

    /** Tempo máximo recomendado (em minutos) até o atendimento médico. */
    public int getTempoMaximoEsperaMin() {
        return tempoMaximoEsperaMin;
    }

    @Override
    public String toString() {
        return name() + " (" + descricao + ", até " + tempoMaximoEsperaMin + " min)";
    }
}
