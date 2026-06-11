package controller;

import enums.TipoCliente;
import model.Cliente;
import model.ClientePJ;
import model.Pessoa;
import util.ClienteRepositorio;
import util.Repositorio;
import view.ClienteView;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteController {

    private static final Logger logger = Logger.getLogger(ClienteController.class.getName());

    private ArrayList<Pessoa> pessoas;
    private ClienteView view;
    private Repositorio<Pessoa> repositorio;

    public ClienteController(ClienteView view) {
        this.pessoas = new ArrayList<>();
        this.view = view;
        this.repositorio = new ClienteRepositorio();
        logger.info("ClienteController inicializado.");
    }

    /** Construtor que permite injetar um repositório customizado (útil para testes). */
    public ClienteController(ClienteView view, Repositorio<Pessoa> repositorio) {
        this.pessoas = new ArrayList<>();
        this.view = view;
        this.repositorio = repositorio;
        logger.info("ClienteController inicializado com repositório customizado.");
    }

    public void cadastrarCliente() {

        String nome = view.lerNome();
        String cpf = view.lerCpf();
        String email = view.lerEmail();
        String telefone = view.lerTelefone();
        int tipo = view.lerTipo();
        if (tipo == 1) {
            Cliente clientePF = new Cliente(nome, cpf, telefone, email, TipoCliente.PF);
            pessoas.add(clientePF);
            logger.info("Cliente PF cadastrado. CPF: " + cpf);
            view.mostrarMsg("Cliente PF cadastrado com sucesso!");
        } else if (tipo == 2) {
            view.LimparBuffer();
            String cnpj = view.lerCnpj();
            String nomeEmpresa = view.lerNomeEmpresa();
            ClientePJ clientePJ = new ClientePJ(nome, cpf, telefone, email, TipoCliente.PJ, cnpj, nomeEmpresa);
            pessoas.add(clientePJ);
            logger.info("Cliente PJ cadastrado. CPF: " + cpf + " | CNPJ: " + cnpj);
            view.mostrarMsg("Cliente PJ cadastrado com sucesso!");
        } else {
            logger.warning("Tipo de cliente inválido informado: " + tipo);
            view.mostrarMsg("Tipo inválido. Cadastro cancelado.");
        }
    }

    public void buscarCPF() {
        String cpf = view.lerCpf();
        boolean encontrado = false;
        for (Pessoa p : pessoas) {
            if (p.getCpf() != null && p.getCpf().equals(cpf)) {
                view.mostrarPessoa(p);
                encontrado = true;
            }
        }
        if (!encontrado) {
            logger.warning("Nenhum cliente encontrado com CPF: " + cpf);
            view.mostrarMsg("Nenhum cliente encontrado.");
        }
    }

    public void buscarCNPJ() {
        String cnpj = view.lerCnpj();
        boolean encontrado = false;
        for (Pessoa p : pessoas) {
            if (p instanceof ClientePJ) {
                ClientePJ pj = (ClientePJ) p;
                if (pj.getCnpj() != null && pj.getCnpj().equals(cnpj)) {
                    view.mostrarPessoa(pj);
                    encontrado = true;
                }
            }
        }
        if (!encontrado) {
            logger.warning("Nenhum cliente PJ encontrado com CNPJ: " + cnpj);
            view.mostrarMsg("Nenhum cliente encontrado.");
        }
    }

    public void listarTodos() {
        logger.info("Listando todos os clientes. Total: " + pessoas.size());
        if (pessoas.isEmpty()) {
            view.mostrarMsg("Nenhum cliente cadastrado.");
        } else {
            view.listarPessoas(pessoas);
        }
    }

    public void listarPF() {
        ArrayList<Pessoa> pfs = new ArrayList<>();
        for (Pessoa p : pessoas) {
            if (p instanceof Cliente && !(p instanceof ClientePJ)) {
                pfs.add(p);
            }
        }
        if (pfs.isEmpty()) {
            view.mostrarMsg("Nenhum cliente PF cadastrado.");
        } else {
            logger.info("Clientes PF encontrados: " + pfs.size());
            view.listarPessoas(pfs);
        }
    }

    public void listarPJ() {
        ArrayList<Pessoa> pjs = new ArrayList<>();
        for (Pessoa p : pessoas) {
            if (p instanceof ClientePJ) {
                pjs.add(p);
            }
        }
        if (pjs.isEmpty()) {
            view.mostrarMsg("Nenhum cliente PJ cadastrado.");
        } else {
            logger.info("Clientes PJ encontrados: " + pjs.size());
            view.listarPessoas(pjs);
        }
    }

    public void removerCliente() {
        String cpf = view.lerCpf();
        Pessoa paraRemover = null;
        for (Pessoa p : pessoas) {
            if (p.getCpf() != null && p.getCpf().equals(cpf)) {
                paraRemover = p;
                break;
            }
        }
        if (paraRemover != null) {
            pessoas.remove(paraRemover);
            logger.info("Cliente removido com sucesso. CPF: " + cpf);
            view.mostrarMsg("Cliente removido com sucesso.");
        } else {
            logger.warning("Cliente não encontrado para remoção. CPF: " + cpf);
            view.mostrarMsg("Cliente não encontrado.");
        }
    }

    public void salvarClientes() {
        try {
            repositorio.salvar(pessoas, "Clientes");
            view.mostrarMsg("Arquivo salvo com todas as informações dos clientes.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Falha ao salvar clientes.", e);
            view.mostrarMsg("Houve um erro ao gravar o arquivo.");
        }
    }

    public void menuClientes() {
        int op = -1;
        do {
            view.menuCliente();
            op = view.lerOp();
            view.LimparBuffer();
            switch (op) {
                case 1: cadastrarCliente();  break;
                case 2: buscarCPF();         break;
                case 3: buscarCNPJ();        break;
                case 4: listarTodos();       break;
                case 5: listarPF();          break;
                case 6: listarPJ();          break;
                case 7: removerCliente();    break;
                case 8: salvarClientes();    break;
                case 0: view.mostrarMsg("Voltando..."); break;
                default: view.mostrarMsg("Opção inválida.");
            }
        } while (op != 0);
    }
}
