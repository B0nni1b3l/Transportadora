package view;

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

}
