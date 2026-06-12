package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FaturaView {

    private final Scanner sc = new Scanner(System.in);

    public void menu() {
        System.out.println("==== MENU FATURA ====");
        System.out.println("1 - Emitir fatura");
        System.out.println("2 - Listar faturas");
        System.out.println("3 - Listar faturas em aberto");
        System.out.println("4 - Marcar fatura como paga");
        System.out.println("5 - Voltar");
    }

    public int lerOp() {
        System.out.print("Opcao: ");
        return lerIntSeguro();
    }

    public String lerCodigoEncomenda() {
        System.out.print("Codigo da encomenda: ");
        return sc.nextLine().trim();
    }

    public String lerCodigoFatura() {
        System.out.print("Codigo da fatura: ");
        return sc.nextLine().trim();
    }

    public double lerDistancia() {
        System.out.print("Distancia da rota: ");
        return lerDoubleSeguro();
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

    private double lerDoubleSeguro() {
        while (true) {
            try {
                double valor = sc.nextDouble();
                sc.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.print("invalido! Tente novamente: ");
            }
        }
    }
}