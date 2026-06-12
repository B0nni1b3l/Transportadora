import controller.ClienteController;
import controller.MotoristaController;
import view.ClienteView;
import view.MotoristaView;
import controller.EncomendaController;
import controller.VeiculoController;
import view.EncomendaView;
import view.VeiculoView;
import util.LogUtil;
import java.util.logging.Logger;
import java.util.Scanner;
import controller.RastreamentoController;
import controller.FaturaController;
import view.RastreamentoView;
import view.FaturaView;

public class Main {
    public static void main(String[] args) {
        Logger logger = LogUtil.getLogger();
        logger.info("Sistema iniciado.");
        ClienteView clienteView = new ClienteView();
        MotoristaView motoristaView = new MotoristaView();
        ClienteController clienteController = new ClienteController(clienteView);
        MotoristaController motoristaController = new MotoristaController(motoristaView);
        VeiculoView veiculoView = new VeiculoView();
        VeiculoController veiculoController = new VeiculoController(veiculoView);
        EncomendaView encomendaView = new EncomendaView();
        EncomendaController encomendaController = new EncomendaController(encomendaView, veiculoController);
        RastreamentoView rastreamentoView = new RastreamentoView();
        FaturaView faturaView = new FaturaView();
        RastreamentoController rastreamentoController = new RastreamentoController(rastreamentoView, encomendaController);
        FaturaController faturaController = new FaturaController(faturaView, encomendaController);
        int op = -1;

        do {
            System.out.println("\n===== SISTEMA TRANSPORTADORA =====");
            System.out.println("1 - Gerenciar Clientes");
            System.out.println("2 - Gerenciar Motoristas");
            System.out.println("3 - Gerenciar Veiculos");
            System.out.println("4 - Gerenciar Encomendas");
            System.out.println("5 - Gerenciar Rastreamento");
            System.out.println("6 - Gerenciar Faturas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            op = clienteView.lerOp();
            clienteView.LimparBuffer();
            switch (op) {
                case 1: clienteController.menuClientes();   break;
                case 2: motoristaController.menuMotoristas(); break;
                case 3: veiculoController.iniciarVeiculo(); break;
                case 4: encomendaController.iniciar(); break;
                case 5: rastreamentoController.iniciar(); break;
                case 6: faturaController.iniciar(); break;
                case 0: System.out.println("Encerrando sistema..."); break;
                default: System.out.println("Opção inválida.");
            }
        } while (op!= 0);
        logger.info("Sistema encerrado.");
        clienteView.fecharScanner();
    }
}
