package view;

import controller.FilialController;
import enums.UF;
import model.Endereco;
import model.Filial;

import java.util.Scanner;

public class FilialView {

    private Scanner scanner;
    private FilialController filialController;

    public FilialView(FilialController filialController) {
        this.filialController = filialController;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n--- MENU FILIAL ---");
            System.out.println("1 - Cadastrar filial");
            System.out.println("2 - Listar filiais");
            System.out.println("3 - Remover filial");
            System.out.println("4 - Adicionar motorista");
            System.out.println("5 - Adicionar encomenda");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                cadastrarFilial();
            } else if (opcao == 2) {
                listarFiliais();
            } else if (opcao == 3) {
                removerFilial();
            } else if (opcao == 4) {
                adicionarMotorista();
            } else if (opcao == 5) {
                adicionarEncomenda();
            } else if (opcao == 0) {
                System.out.println("Voltando...");
            } else {
                System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    public void cadastrarFilial() {
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();

        System.out.print("Logradouro: ");
        String logradouro = scanner.nextLine();

        System.out.print("Número: ");
        String numero = scanner.nextLine();

        System.out.print("CEP: ");
        String cep = scanner.nextLine();

        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();

        System.out.print("UF (PR, SP, RJ, SC, RS, ES, MG): ");
        UF uf = UF.valueOf(scanner.nextLine().toUpperCase());

        Endereco endereco = new Endereco(logradouro, numero, cep, cidade, uf);
        Filial filial = new Filial(cnpj, endereco);

        filialController.cadastrar(filial);

        System.out.println("Filial cadastrada com sucesso.");
    }

    public void listarFiliais() {
        for (Filial filial : filialController.listar()) {
            filial.exibirDados();
            System.out.println("------------------");
        }
    }

    public void removerFilial() {
        System.out.print("Digite o CNPJ da filial: ");
        String cnpj = scanner.nextLine();

        boolean removido = filialController.remover(cnpj);

        if (removido) {
            System.out.println("Filial removida.");
        } else {
            System.out.println("Filial não encontrada.");
        }
    }

    public void adicionarMotorista() {
        System.out.print("CNPJ da filial: ");
        String cnpj = scanner.nextLine();

        Filial filial = filialController.buscarPorCnpj(cnpj);

        if (filial == null) {
            System.out.println("Filial não encontrada.");
        } else {
            System.out.print("Nome do motorista: ");
            String motorista = scanner.nextLine();

            filial.adicionarMotorista(motorista);

            System.out.println("Motorista adicionado.");
        }
    }

    public void adicionarEncomenda() {
        System.out.print("CNPJ da filial: ");
        String cnpj = scanner.nextLine();

        Filial filial = filialController.buscarPorCnpj(cnpj);

        if (filial == null) {
            System.out.println("Filial não encontrada.");
        } else {
            System.out.print("Código da encomenda: ");
            String encomenda = scanner.nextLine();

            filial.adicionarEncomenda(encomenda);

            System.out.println("Encomenda adicionada.");
        }
    }
}
