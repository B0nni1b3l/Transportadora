package view;

import controller.SuporteController;
import enums.TipoChamado;
import exceptions.ChamadoInexistenteException;
import model.Suporte;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SuporteView {
    private final SuporteController controller;
    private final Scanner scanner;

    public SuporteView() {
        this.controller = new SuporteController();
        this.controller.iniciarDadosSuporte();
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao = -1;
        do {
            try {
                System.out.println("\n===== MENU SUPORTE (SAC) =====");
                System.out.println("1 - Abrir chamado");
                System.out.println("2 - Listar chamados");
                System.out.println("3 - Atualizar status do chamado");
                System.out.println("0 - Voltar");
                System.out.print("Escolha uma opção: ");

                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        abrirChamado();
                        break;
                    case 2:
                        listarChamados();
                        break;
                    case 3:
                        atualizarStatus();
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

    private void abrirChamado() {
        System.out.print("Código da Encomenda: ");
        String cod = scanner.nextLine();
        System.out.print("CPF do Cliente: ");
        String cpf = scanner.nextLine();

        System.out.println("Tipo do Chamado (1 - AVARIA, 2 - ATRASO, 3 - COBRANÇA, 4 - OUTROS): ");
        int tipoOp = scanner.nextInt();
        scanner.nextLine();

        TipoChamado tipo;
        switch (tipoOp) {
            case 1:
                tipo = TipoChamado.AVARIA_PRODUTO;
                break;
            case 2:
                tipo = TipoChamado.ATRASO_ENTREGA;
                break;
            case 3:
                tipo = TipoChamado.PROBLEMA_COBRANCA;
                break;
            default:
                tipo = TipoChamado.OUTROS;
                break;
        }

        System.out.print("Mensagem de Reclamação: ");
        String msg = scanner.nextLine();

        controller.abrirChamado(cod, cpf, tipo, msg);
        System.out.println("Chamado de suporte registrado com sucesso.");
    }

    private void listarChamados() {
        if (controller.listarTodos().isEmpty()) {
            System.out.println("Nenhum ticket de suporte em aberto.");
            return;
        }
        for (Suporte s : controller.listarTodos().values()) {
            System.out.println(s.toString());
        }
    }

    private void atualizarStatus() {
        try {
            System.out.print("Digite o ID do chamado: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Novo Status (ex: EM_ANALISE / FINALIZADO): ");
            String status = scanner.nextLine();

            if (controller.atualizarStatusChamado(id, status)) {
                System.out.println("Status do chamado modificado.");
            }
        } catch (ChamadoInexistenteException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
