package br.ceub.poo.upa.cli;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Leitura SEGURA de dados do teclado. JÁ IMPLEMENTADA — estude os try/catch!
 *
 * <p>Regra de ouro: usamos SEMPRE {@code nextLine()} e convertemos o texto.
 * Misturar {@code nextInt()} com {@code nextLine()} deixa um "\n" no buffer
 * e causa o famoso bug da leitura "pulada".</p>
 *
 * <p>Cada método repete a pergunta até receber um valor válido (laço + tratamento de exceção).</p>
 */
public class Console {

    public static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");

    private final Scanner scanner;

    public Console(Scanner scanner) {
        this.scanner = scanner;
    }

    public String lerTexto(String rotulo) {
        while (true) {
            System.out.print(rotulo + ": ");
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("  [!] Campo obrigatório.");
        }
    }

    public int lerInteiro(String rotulo, int minimo, int maximo) {
        while (true) {
            String texto = lerTexto(rotulo + " [" + minimo + "-" + maximo + "]");
            try {
                int valor = Integer.parseInt(texto);
                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }
                System.out.println("  [!] Informe um valor entre " + minimo + " e " + maximo + ".");
            } catch (NumberFormatException e) {
                System.out.println("  [!] Digite apenas números inteiros.");
            }
        }
    }

    /** Aceita vírgula ou ponto como separador decimal (37,5 ou 37.5). */
    public double lerDecimal(String rotulo) {
        while (true) {
            String texto = lerTexto(rotulo).replace(',', '.');
            try {
                return Double.parseDouble(texto);
            } catch (NumberFormatException e) {
                System.out.println("  [!] Número inválido. Ex.: 37,5");
            }
        }
    }

    public LocalDate lerData(String rotulo) {
        while (true) {
            String texto = lerTexto(rotulo + " (dd/mm/aaaa)");
            try {
                LocalDate data = LocalDate.parse(texto, FORMATO_DATA);
                if (!data.isAfter(LocalDate.now())) {
                    return data;
                }
                System.out.println("  [!] A data não pode estar no futuro.");
            } catch (DateTimeParseException e) {
                System.out.println("  [!] Data inválida. Use o formato dd/mm/aaaa.");
            }
        }
    }

    public boolean lerSimNao(String rotulo) {
        while (true) {
            String texto = lerTexto(rotulo + " (s/n)").toLowerCase();
            if (texto.equals("s") || texto.equals("sim")) {
                return true;
            }
            if (texto.equals("n") || texto.equals("nao") || texto.equals("não")) {
                return false;
            }
            System.out.println("  [!] Responda s ou n.");
        }
    }

    public void pausar() {
        System.out.print("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }
}
