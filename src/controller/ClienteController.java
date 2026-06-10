package controller;

import enums.TipoCliente;
import model.Cliente;
import model.ClientePJ;
import model.Pessoa;
import view.ClienteView;

import java.util.ArrayList;

public class ClienteController {
    private ArrayList<Pessoa> pessoas;
    ClienteView view;
    public ClienteController(ClienteView view){
        pessoas = new ArrayList<>();
        this.view = view;
    }
    public void cadastrarCliente(){
        String nome = view.lerNome();
        String cpf = view.lerCpf();
        String email = view.lerEmail();
        String telefone = view.lerTelefone();
        int tipo = view.lerTipo();
        view.LimparBuffer();
        if(tipo==1){
            Cliente clientePF = new Cliente(nome, cpf, telefone, email, TipoCliente.PF);
            pessoas.add(clientePF);
            view.mostrarMsg("Cliente PF cadastrado com sucesso!");
        } else if(tipo==2){
            String cnpj = view.lerCnpj();
            String nomeEmpresa = view.lerNomeEmpresa();
            ClientePJ clientePJ = new ClientePJ(nome, cpf, telefone, email, TipoCliente.PJ, cnpj, nomeEmpresa);
            pessoas.add(clientePJ);
            view.mostrarMsg("Cliente PJ cadastrado com sucesso!");
        }
    }
    public void buscarCPF(){
        String cpf = view.lerCpf();
        boolean encontrado = false;
        for (Pessoa p : pessoas) {
            if (p.getCpf() != null && p.getCpf().equals(cpf)) {
                view.mostrarPessoa(p);
                encontrado = true;
            }
        }
        if (!encontrado) {
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
            view.mostrarMsg("Nenhum cliente encontrado.");
        }
    }
    public void listarTodos() {
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
            view.mostrarMsg("Cliente removido com sucesso.");
        } else {
            view.mostrarMsg("Cliente não encontrado.");
        }
    }
    public void menuClientes() {
        int op = -1;
        do {
            view.menuCliente();
            op = view.lerOp();
            switch (op) {
                case 1: cadastrarCliente(); break;
                case 2: buscarCPF();     break;
                case 3: buscarCNPJ();    break;
                case 4: listarTodos();      break;
                case 5: listarPF();         break;
                case 6: listarPJ();         break;
                case 7: removerCliente();   break;
                case 0: view.mostrarMsg("Voltando..."); break;
                default: view.mostrarMsg("Opção inválida.");
            }
        } while (op != 0);
    }
}
