package view;

import enums.EventoRastreamento;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RastreamentoView {
    private final Scanner sc = new Scanner(System.in);

    public void menu() {
        System.out.println("==== MENU RASTREAMENTO ====");
        System.out.println("1 - Registrar evento");
        System.out.println("2 - Buscar historico");
        System.out.println("3 - Voltar");
    }

    public int lerOp() {
        System.out.print("Opcao: ");
        return lerIntSeguro();
    }

    public String lerCodigoEncomenda() {
        System.out.print("Codigo da encomenda: ");
        return sc.nextLine().trim();
    }

    public String lerLocalizacao() {
        System.out.print("Localizacao atual: ");
        return sc.nextLine().trim();
    }

    public EventoRastreamento lerEvento() {
        System.out.println("Evento:");
        System.out.println("1 - Postado");
        System.out.println("2 - Em transferencia");
        System.out.println("3 - Saiu para entrega");
        System.out.println("4 - Entregue");
        System.out.println("5 - Devolvido");
        System.out.print("Escolha: ");

        int op = lerIntSeguro();
        return switch (op) {
            case 2 -> EventoRastreamento.EM_TRANSFERENCIA;
            case 3 -> EventoRastreamento.SAIU_PARA_ENTREGA;
            case 4 -> EventoRastreamento.ENTREGUE;
            case 5 -> EventoRastreamento.DEVOLVIDO;
            default -> EventoRastreamento.POSTADO;
        };
    }

    public void mostrarMsg(String msg) {
        System.out.println(msg);
    }

    private int lerIntSeguro() {
        while (true) {
            try {
                int valor = sc.nextInt();
                sc.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.print("invalido! Tente novamente: ");
            }
        }
    }
}
