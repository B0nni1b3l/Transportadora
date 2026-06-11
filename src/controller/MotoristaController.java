package controller;

import model.Motorista;
import util.MotoristaRepositorio;
import util.Repositorio;
import view.MotoristaView;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MotoristaController {
    private static final Logger logger = Logger.getLogger(MotoristaController.class.getName());
    private ArrayList<Motorista> motoristas;
    private MotoristaView view;
    private Repositorio<Motorista> repositorio;
    public MotoristaController(MotoristaView view) {
        this.motoristas = new ArrayList<>();
        this.view = view;
        this.repositorio = new MotoristaRepositorio();
        logger.info("MotoristaController inicializado.");
    }
    public MotoristaController(MotoristaView view, Repositorio<Motorista> repositorio) {
        this.motoristas = new ArrayList<>();
        this.view = view;
        this.repositorio = repositorio;
        logger.info("MotoristaController inicializado com repositório customizado.");
    }
    public void cadastrarMotorista() {
        String nome = view.lerNome();
        String cpf = view.lerCpf();
        String telefone = view.lerTelefone();
        String email = view.lerEmail();
        String numeroCNH = view.lerNumeroCNH();
        String categoriaCNH = view.lerCategoriaCNH();
        Motorista motorista = new Motorista(nome, cpf, telefone, email, numeroCNH, categoriaCNH, true);
        motoristas.add(motorista);
        logger.info("Motorista cadastrado. CPF: " + cpf + " | CNH: " + numeroCNH);
        view.mostrarMsg("Motorista cadastrado com sucesso!");
    }
    public void buscarPorCPF() {
        String cpf = view.lerCpf();
        boolean encontrado = false;
        for (Motorista m : motoristas) {
            if (m.getCpf() != null && m.getCpf().equals(cpf)) {
                view.mostrarMotorista(m);
                encontrado = true;
            }
        }
        if (!encontrado) {
            logger.warning("Nenhum motorista encontrado com CPF: " + cpf);
            view.mostrarMsg("Nenhum motorista encontrado.");
        }
    }
    public void listarTodos() {
        if (motoristas.isEmpty()) {
            view.mostrarMsg("Nenhum motorista cadastrado.");
        } else {
            view.listarTodos(motoristas);
        }
    }
    public void listarDisponiveis() {
        ArrayList<Motorista> disponiveis = new ArrayList<>();
        for (Motorista m : motoristas) {
            if (m.isDisponivel()) {
                disponiveis.add(m);
            }
        }
        if (disponiveis.isEmpty()) {
            view.mostrarMsg("Nenhum motorista disponível no momento.");
        } else {
            logger.info("Motoristas disponíveis: " + disponiveis.size());
            view.listarTodos(disponiveis);
        }
    }
    public void alterarDisponibilidade() {
        String cpf = view.lerCpf();
        for (Motorista m : motoristas) {
            if (m.getCpf() != null && m.getCpf().equals(cpf)) {
                m.setDisponivel(!m.isDisponivel());
                String status = m.isDisponivel() ? "disponível" : "indisponível";
                logger.info("Motorista CPF " + cpf + " agora está: " + status);
                view.mostrarMsg("Motorista agora está " + status + ".");
                return;
            }
        }
        logger.warning("Motorista não encontrado para alterar disponibilidade. CPF: " + cpf);
        view.mostrarMsg("Motorista não encontrado.");
    }
    public void removerMotorista() {
        String cpf = view.lerCpf();
        Motorista paraRemover = null;
        for (Motorista m : motoristas) {
            if (m.getCpf() != null && m.getCpf().equals(cpf)) {
                paraRemover = m;
                break;
            }
        }
        if (paraRemover != null) {
            motoristas.remove(paraRemover);
            view.mostrarMsg("Motorista removido com sucesso.");
        } else {
            logger.warning("Motorista não encontrado para remoção. CPF: " + cpf);
            view.mostrarMsg("Motorista não encontrado.");
        }
    }
    public void salvarMotoristas() {
        try {
            repositorio.salvar(motoristas, "Motoristas");
            view.mostrarMsg("Arquivo salvo com todas as informações dos motoristas.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Falha ao salvar motoristas.", e);
            view.mostrarMsg("Houve um erro ao gravar o arquivo.");
        }
    }
    public void menuMotoristas() {
        int op = -1;
        do {
            view.menuMotorista();
            op = view.lerOp();
            view.LimparBuffer();
            switch (op) {
                case 1: cadastrarMotorista();   break;
                case 2: buscarPorCPF(); break;
                case 3: listarTodos();  break;
                case 4: listarDisponiveis();    break;
                case 5: alterarDisponibilidade();   break;
                case 6: removerMotorista(); break;
                case 7: salvarMotoristas(); break;
                case 0: view.mostrarMsg("Voltando..."); break;
                default:
                    logger.warning("Opção inválida no menu de motoristas: " + op);
                    view.mostrarMsg("Opção inválida.");
            }
        } while (op != 0);
    }
}
