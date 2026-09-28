package br.ceub.poo.upa.triagem;

import br.ceub.poo.upa.excecao.SinaisVitaisInvalidosException;
import br.ceub.poo.upa.modelo.Prioridade;
import br.ceub.poo.upa.modelo.SinaisVitais;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Testes FORNECIDOS pelo professor. Todos devem passar ao final. */
class ClassificadorManchesterSimplificadoTest {

    private final ClassificadorRisco classificador = new ClassificadorManchesterSimplificado();

    /** Sinais "normais": temp 36.5, FC 80, PA 120/80, SpO2 98, dor 0, consciente. */
    private SinaisVitais sinais(double temp, int fc, int pas, int spo2, int dor, boolean consciente)
            throws SinaisVitaisInvalidosException {
        return new SinaisVitais(temp, fc, pas, Math.min(80, pas - 10), spo2, dor, consciente);
    }

    @Test
    void semAlteracoesEhAzul() throws Exception {
        assertEquals(Prioridade.AZUL, classificador.classificar(sinais(36.5, 80, 120, 98, 0, true)));
    }

    @Test
    void inconscienteEhVermelho() throws Exception {
        assertEquals(Prioridade.VERMELHO, classificador.classificar(sinais(36.5, 80, 120, 98, 0, false)));
    }

    @Test
    void saturacaoNoLimite() throws Exception {
        assertEquals(Prioridade.VERMELHO, classificador.classificar(sinais(36.5, 80, 120, 89, 0, true)));
        assertEquals(Prioridade.LARANJA, classificador.classificar(sinais(36.5, 80, 120, 90, 0, true)));
        assertEquals(Prioridade.LARANJA, classificador.classificar(sinais(36.5, 80, 120, 93, 0, true)));
        assertEquals(Prioridade.AZUL, classificador.classificar(sinais(36.5, 80, 120, 94, 0, true)));
    }

    @Test
    void febreAltaEhAmareloEFebreMuitoAltaEhLaranja() throws Exception {
        assertEquals(Prioridade.AMARELO, classificador.classificar(sinais(38.5, 80, 120, 98, 0, true)));
        assertEquals(Prioridade.LARANJA, classificador.classificar(sinais(40.0, 80, 120, 98, 0, true)));
    }

    @Test
    void dorDefineCor() throws Exception {
        assertEquals(Prioridade.VERDE, classificador.classificar(sinais(36.5, 80, 120, 98, 3, true)));
        assertEquals(Prioridade.AMARELO, classificador.classificar(sinais(36.5, 80, 120, 98, 6, true)));
        assertEquals(Prioridade.LARANJA, classificador.classificar(sinais(36.5, 80, 120, 98, 9, true)));
    }

    @Test
    void regraMaisGraveVence() throws Exception {
        // dor 3 (VERDE) + FC 160 (VERMELHO) → VERMELHO
        assertEquals(Prioridade.VERMELHO, classificador.classificar(sinais(36.5, 160, 120, 98, 3, true)));
    }
}
