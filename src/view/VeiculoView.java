package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class VeiculoView {

    private final Scanner sc = new Scanner(System.in);

    public int lerTipo() {
        System.out.println("Tipo de veículo:\n1 - Caminhão\n2 - Van");
        System.out.print("Escolha: ");
        return lerIntSeguro();
    }

    public String lerPlaca() {
        System.out.print("Placa: ");
        return sc.nextLine().trim();
    }

    public double lerCapacidadeKg() {
        System.out.print("Capacidade (kg): ");
        return lerDoubleSeguro();
    }

    public String lerDisponivel() {
        System.out.print("Disponível? (Disponível/Indisponível): ");
        return sc.nextLine().trim();
    }

    public int lerNumeroDeEixos() {
        System.out.print("Número de eixos: ");
        return lerIntSeguro();
    }

    public String lerTipoCacamba() {
        System.out.print("Tipo de caçamba: ");
        return sc.nextLine().trim();
    }

    public String lerEntregaUrbana() {
        System.out.print("Entrega urbana? (SIM/NAO): ");
        return sc.nextLine().trim();
    }

    public void mostrarMsg(String msg) {
        System.out.println(msg);
    }

    public int lerOp() {
        System.out.print("Opção: ");
        return lerIntSeguro();
    }

    public void menu() {
        System.out.println("====MENU FROTA====");
        System.out.println("1 - Cadastrar veículo");
        System.out.println("2 - Listar veículos");
        System.out.println("3 - Atualizar disponibilidade");
        System.out.println("4 - Excluir veículo");
        System.out.println("5 - Verificar capacidade");
        System.out.println("6 - Voltar");
    }

    private int lerIntSeguro() {
        while (true) {
            try {
                int v = sc.nextInt();
                sc.nextLine();
                return v;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.print("Valor inválido. Tente novamente: ");
            }
        }
    }

    private double lerDoubleSeguro() {
        while (true) {
            try {
                double v = sc.nextDouble();
                sc.nextLine();
                return v;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.print("Valor inválido. Tente novamente: ");
            }
        }
    }
}