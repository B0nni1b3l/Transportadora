package view;

import enums.StatusEncomenda;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EncomendaView {

    private final Scanner sc = new Scanner(System.in);

    public String lerCodigo() {
        System.out.print("Código de rastreio: ");
        return sc.nextLine().trim();
    }

    public double lerPeso() {
        System.out.print("Peso (kg): ");
        return lerDoubleSeguro();
    }

    public String lerDimensoes() {
        System.out.print("Dimensões (ex: 30x20x15 cm): ");
        return sc.nextLine().trim();
    }

    public StatusEncomenda lerStatus() {
        System.out.println("Status:\n1 - Aguardando\n2 - Em Trânsito\n3 - Entregue\n4 - Devolvida");
        System.out.print("Escolha: ");
        int op = lerIntSeguro();
        return switch (op) {
            case 1 -> StatusEncomenda.AGUARDANDO;
            case 2 -> StatusEncomenda.EM_TRANSITO;
            case 3 -> StatusEncomenda.ENTREGUE;
            case 4 -> StatusEncomenda.DEVOLVIDA;
            default -> StatusEncomenda.AGUARDANDO;
        };
    }

    public String lerCodigoBusca() {
        System.out.print("Código de rastreio: ");
        return sc.nextLine().trim();
    }

    public String lerPlacaVeiculo() {
        System.out.print("Placa do veículo: ");
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
        System.out.println("====MEUNU ENCOMENDAS====");
        System.out.println("1 - Cadastrar encomenda");
        System.out.println("2 - Listar encomendas");
        System.out.println("3 - Atualizar status");
        System.out.println("4 - Atribuir veículo");
        System.out.println("5 - Excluir encomenda");
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