package br.ceub.poo.upa.estrutura;

/**
 * Nó de uma lista encadeada simples: guarda um valor e a referência para o próximo nó.
 *
 *     [valor|proximo] ──► [valor|proximo] ──► [valor|null]
 *
 * <p>Visibilidade de PACOTE (sem "public"): só as estruturas deste pacote usam.
 * É encapsulamento no nível de pacote. JÁ IMPLEMENTADO.</p>
 */
class No<T> {

    T valor;
    No<T> proximo;

    No(T valor) {
        this.valor = valor;
    }
}
