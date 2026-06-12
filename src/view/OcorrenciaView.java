package view;

import controller.OcorrenciaController;
import exceptions.OcorrenciaInvalidaException;
import model.Ocorrencia;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OcorrenciaView {
    private final OcorrenciaController controller;
    private final Scanner scanner;

    public OcorrenciaView() {
        this.controller = new OcorrenciaController();
        this.controller.iniciarDadosOcorrencia();
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao = -1;
        do {
            try {
                System.out.println("\n===== MENU OCORRÊNCIA =====");
                System.out.println("1 - Registrar ocorrência");
                System.out.println("2 - Listar ocorrências");
                System.out.println("3 - Resolver ocorrência");
                System.out.println("0 - Voltar");
                System.out.print("Escolha uma opção: ");

                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        registrarOcorrencia();
                        break;
                    case 2:
                        listarOcorrencias();
                        break;
                    case 3:
                        resolverOcorrencia();
                        break;
                    case 0:
                        System.out.println("A voltar ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: digite um número válido.");
                scanner.nextLine();
            }
        } while (opcao != 0);
    }

    private void registrarOcorrencia() {
        try {
            System.out.print("Código da Encomenda: ");
            String cod = scanner.nextLine();
            System.out.print("Descrição do Incidente: ");
            String desc = scanner.nextLine();

            controller.registrarOcorrencia(cod, desc);
            System.out.println("Ocorrência cadastrada com sucesso.");
        } catch (OcorrenciaInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarOcorrencias() {
        if (controller.listarTodas().isEmpty()) {
            System.out.println("Nenhuma ocorrência sob registro.");
            return;
        }
        for (Ocorrencia o : controller.listarTodas()) {
            System.out.println(o.toString());
        }
    }

    private void resolverOcorrencia() {
        System.out.print("Digite o ID da ocorrência a resolver: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (controller.resolverOcorrencia(id)) {
            System.out.println("Status alterado para resolvido com sucesso.");
        } else {
            System.out.println("Ocorrência não encontrada no sistema.");
        }
    }
}