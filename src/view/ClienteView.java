package view;

import model.Cliente;
import model.ClientePJ;
import model.Pessoa;

import java.sql.SQLOutput;
import java.util.*;

public class ClienteView {
    private Scanner sc;
    public ClienteView(){
        sc = new Scanner(System.in);
    }
    public void menuCliente(){
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
        System.out.println("Escolha o tipo:\n1 - PF (Pessoa Física)\n2 - PJ (Pessoa Jurídica)");
        return sc.nextInt();
    }
    public String lerCnpj(){
        System.out.println("Digite o CNPJ: ");
        return sc.nextLine();
    }
    public String lerNomeEmpresa(){
        System.out.println("Digite o nome da Empresa ");
        return sc.nextLine();
    }
    public int lerOp(){
        return sc.nextInt();
    }
    public void mostrarMsg(String msg){
        System.out.println(msg);
    }
    public void mostrarPessoa(Pessoa p) {
        System.out.println(p);
    }
    public void LimparBuffer(){
        sc.nextLine();
    }
    public void listarPessoas(ArrayList<Pessoa> lista) {
        System.out.println("\n===Lista de Clientes===");
        for (Pessoa p : lista) {
            p.exibirDados();
        }
    }
}