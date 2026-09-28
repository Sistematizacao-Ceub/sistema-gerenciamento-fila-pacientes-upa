package br.ceub.poo.upa.modelo;

import java.time.LocalDate;
import java.time.Period;

/**
 * Superclasse ABSTRATA de todas as pessoas do sistema (pacientes e profissionais).
 *
 * <p>CONCEITOS:</p>
 * <ul>
 *   <li><b>Classe abstrata</b>: não pode ser instanciada ({@code new Pessoa(...)} não compila).
 *       Ela existe para concentrar o que é COMUM às subclasses.</li>
 *   <li><b>Encapsulamento</b>: atributos {@code private}; acesso só por getters.
 *       Os atributos são {@code final} porque nome, CPF e nascimento não mudam.</li>
 *   <li><b>Método abstrato</b>: {@link #getIdentificacao()} OBRIGA cada subclasse a
 *       fornecer sua própria implementação → base do <b>polimorfismo</b>.</li>
 *   <li><b>protected</b>: o construtor só é visível para as subclasses (e o pacote).</li>
 * </ul>
 *
 * <p>JÁ IMPLEMENTADA — é o modelo de referência para as outras classes.</p>
 */
public abstract class Pessoa {

    private final String nome;
    private final String cpf;
    private final LocalDate dataNascimento;

    protected Pessoa(String nome, String cpf, LocalDate dataNascimento) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        this.nome = nome.trim();
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    /** Idade em anos completos na data de hoje. */
    public int getIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    /**
     * Texto que identifica a pessoa na tela. Cada subclasse decide o formato
     * (ex.: "Dr(a). Ana Souza — CRM-DF 12345"). POLIMORFISMO.
     */
    public abstract String getIdentificacao();

    @Override
    public String toString() {
        return getIdentificacao(); // chama a versão da SUBCLASSE (ligação dinâmica)
    }
}
