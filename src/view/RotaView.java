package view;

import controller.FilialController;
import controller.RotaController;
import model.Filial;
import model.Rota;

import java.util.Scanner;

public class RotaView {

    private Scanner scanner;
    private RotaController rotaController;
    private FilialController filialController;

    public RotaView(RotaController rotaController, FilialController filialController) {
        this.rotaController = rotaController;
        this.filialController = filialController;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n--- MENU ROTA ---");
            System.out.println("1 - Cadastrar rota");
            System.out.println("2 - Listar rotas");
            System.out.println("3 - Remover rota");
            System.out.println("4 - Calcular frete");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                cadastrarRota();
            } else if (opcao == 2) {
                listarRotas();
            } else if (opcao == 3) {
                removerRota();
            } else if (opcao == 4) {
                calcularFrete();
            } else if (opcao == 0) {
                System.out.println("Voltando...");
            } else {
                System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    public void cadastrarRota() {
        System.out.print("CNPJ da filial de origem: ");
        String cnpjOrigem = scanner.nextLine();

        System.out.print("CNPJ da filial de destino: ");
        String cnpjDestino = scanner.nextLine();

        Filial origem = filialController.buscarPorCnpj(cnpjOrigem);
        Filial destino = filialController.buscarPorCnpj(cnpjDestino);

        if (origem == null || destino == null) {
            System.out.println("Filial de origem ou destino não encontrada.");
        } else {
            System.out.print("Distância em KM: ");
            double distancia = scanner.nextDouble();

            System.out.print("Prazo em dias: ");
            int prazo = scanner.nextInt();
            scanner.nextLine();

            Rota rota = new Rota(origem, destino, distancia, prazo);
            rotaController.cadastrar(rota);

            System.out.println("Rota cadastrada com sucesso.");
        }
    }

    public void listarRotas() {
        for (Rota rota : rotaController.listar()) {
            rota.exibirDados();
            System.out.println("------------------");
        }
    }

    public void removerRota() {
        System.out.print("CNPJ da origem: ");
        String origem = scanner.nextLine();

        System.out.print("CNPJ do destino: ");
        String destino = scanner.nextLine();

        boolean removido = rotaController.remover(origem, destino);

        if (removido) {
            System.out.println("Rota removida.");
        } else {
            System.out.println("Rota não encontrada.");
        }
    }

    public void calcularFrete() {
        System.out.print("CNPJ da origem: ");
        String cnpjOrigem = scanner.nextLine();

        System.out.print("CNPJ do destino: ");
        String cnpjDestino = scanner.nextLine();

        Rota rota = rotaController.buscarPorFiliais(cnpjOrigem, cnpjDestino);

        if (rota == null) {
            System.out.println("Rota não encontrada.");
        } else {
            System.out.print("Peso da encomenda: ");
            double peso = scanner.nextDouble();
            scanner.nextLine();

            double frete = rotaController.calcularFrete(rota, peso);

            System.out.println("Valor do frete: R$ " + frete);
        }
    }
}
