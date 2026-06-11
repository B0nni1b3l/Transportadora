import controller.ClienteController;
import controller.MotoristaController;
import view.ClienteView;
import view.MotoristaView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClienteView clienteView = new ClienteView();
        MotoristaView motoristaView = new MotoristaView();
        ClienteController clienteController = new ClienteController(clienteView);
        MotoristaController motoristaController = new MotoristaController(motoristaView);
        int op = -1;

        do {
            System.out.println("\n===== SISTEMA TRANSPORTADORA =====");
            System.out.println("1 - Gerenciar Clientes");
            System.out.println("2 - Gerenciar Motoristas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            op = clienteView.lerOp();
            clienteView.LimparBuffer();
            switch (op) {
                case 1: clienteController.menuClientes();   break;
                case 2: motoristaController.menuMotoristas(); break;
                case 0: System.out.println("Encerrando sistema..."); break;
                default: System.out.println("Opção inválida.");
            }
        } while (op!= 0);

        clienteView.fecharScanner();
    }
}
