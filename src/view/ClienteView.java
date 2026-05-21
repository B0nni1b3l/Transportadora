package view;

import model.Cliente;
import model.ClientePJ;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class ClienteView {
    private Scanner sc;
    public ClienteView(){
        sc = new Scanner(System.in);
    }
    public void MenuCliente(){
        System.out.println("\n===== MENU CLIENTE =====");
        System.out.println("1 - Cadastrar cliente");
        System.out.println("2 - Buscar por CPF");
        System.out.println("3 - Buscar por CNPJ");
        System.out.println("4 - Listar todos");
        System.out.println("5 - Listar PF");
        System.out.println("6 - Listar PJ");
        System.out.println("7 - Remover cliente");
        System.out.println("0 - Voltar");
        System.out.print("Escolha uma opção: ");
    }
    public String lerNome(){
        System.out.println("Digite o nome da pessoa: ");
        return sc.nextLine();
    }
    public String lerCpf(){
        System.out.println("Digite o CPF: ");
        return sc.nextLine();
    }
    public String lerTelefone(){
        System.out.println("Digite o número de telefone: ");
        return sc.nextLine();
    }
    public String lerEmail(){
        System.out.println("Digite o email: ");
        return sc.nextLine();
    }
    public int lerTipo(){
        System.out.println("Escolha o tipo:\n[1]PF\n[2]PJ");
        int tipo = sc.nextInt();
        sc.nextLine();
        return tipo;
    }
    public String lerCnpj(){
        System.out.println("Digite o CNPJ: ");
        return sc.nextLine();
    }
    public String lerNomeEmpresa(){
        System.out.println("Digite o nome da Empresa ");
        return sc.nextLine();
    }
    public void mostrarPF(Cliente c){
        System.out.println("Nome: " + c.getNome() + "\nCPF: " + c.getCpf() + "\nTelefone: " + c.getTelefone() + "\nEmail: " + c.getEmail() + "\n----------------------");
    }
    public void mostrarPJ(ClientePJ c){
        System.out.println("Nome: " + c.getNome() + "\nCPF: " + c.getCpf() + "\nTelefone: " + c.getTelefone() + "\nEmail: " + c.getEmail() + "\nCNPJ: " + c.getCnpj() + "\nNome da empresa: " + c.getNomeEmpresa());
    }
    public void listarPF(ArrayList<Cliente> pfs){
        System.out.println("Lista de PF:");
        if(pfs.isEmpty()){
            for(Cliente c: pfs){
                mostrarPF(c);
            }
        }
    }
    public void listarPJ(ArrayList<ClientePJ> pjs){
        System.out.println("Lista de PJ:");
        if(!pjs.isEmpty()){
            for(ClientePJ c: pjs){
                mostrarPJ(c);
            }
        }
    }
    public void listarTodos(ArrayList<Cliente> pfs, ArrayList<ClientePJ> pjs){
        System.out.println("Lista de todos os clientes: ");
        if(!pfs.isEmpty()){
            for(Cliente c: pfs){
                mostrarPF(c);
            }
        }
        if(!pjs.isEmpty()){
            for(ClientePJ c: pjs){
                mostrarPJ(c);
            }
        }
    }
}
