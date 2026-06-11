package view;

import model.Motorista;

import java.util.ArrayList;
import java.util.Scanner;

public class MotoristaView {
    private Scanner sc;
    public MotoristaView(){
        sc = new Scanner(System.in);
    }
    public void menuMotorista(){
        System.out.println("\n===== MENU MOTORISTA =====");
        System.out.println("1 - Cadastrar motorista");
        System.out.println("2 - Buscar por CPF");
        System.out.println("3 - Listar todos");
        System.out.println("4 - Listar disponíveis");
        System.out.println("5 - Alterar disponibilidade");
        System.out.println("6 - Remover motorista");
        System.out.println("7 - Salvar motoristas");
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
    public String lerNumeroCNH(){
        System.out.println("Digite o número da CNH: ");
        return sc.nextLine();
    }
    public String lerCategoriaCNH(){
        System.out.println("Digite a categoria da CNH: ");
        return sc.nextLine();
    }
    public int lerOp(){
        return sc.nextInt();
    }
    public void mostrarMotorista(Motorista m) {
        System.out.println(m);
    }
    public void LimparBuffer(){
        sc.nextLine();
    }
    public void listarTodos(ArrayList<Motorista> motoristas) {
        System.out.println("\n===Lista de Motoristas===");
        if (motoristas.isEmpty()) {
            System.out.println("Nenhum motorista encontrado.");
        } else {
            for (Motorista m : motoristas) {
                m.exibirDados();
            }
        }
    }
    public void mostrarMsg(String msg) {
        System.out.println(msg);
    }
}
