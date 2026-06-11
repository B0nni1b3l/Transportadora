package model;

import java.util.ArrayList;

public class Filial {

    private String cnpj;
    private Endereco endereco;

    private ArrayList<String> motoristas;
    private ArrayList<String> encomendas;

    public Filial(String cnpj, Endereco endereco) {

        setCnpj(cnpj);
        setEndereco(endereco);

        motoristas = new ArrayList<>();
        encomendas = new ArrayList<>();
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {

        if (cnpj.isEmpty()) {
            System.out.println("CNPJ inválido.");
        } else {
            this.cnpj = cnpj;
        }
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public ArrayList<String> getMotoristas() {
        return motoristas;
    }

    public ArrayList<String> getEncomendas() {
        return encomendas;
    }

    public void adicionarMotorista(String motorista) {

        if (motorista.isEmpty()) {
            System.out.println("Motorista inválido.");
        } else {
            motoristas.add(motorista);
        }
    }

    public void adicionarEncomenda(String encomenda) {

        if (encomenda.isEmpty()) {
            System.out.println("Encomenda inválida.");
        } else {
            encomendas.add(encomenda);
        }
    }

    public void listarMotoristas() {

        for (String motorista : motoristas) {
            System.out.println(motorista);
        }
    }

    public void listarEncomendas() {

        for (String encomenda : encomendas) {
            System.out.println(encomenda);
        }
    }

    public void exibirDados() {

        System.out.println("CNPJ: " + cnpj);
        System.out.println("Cidade: " + endereco.getCidade());
        System.out.println("UF: " + endereco.getUf());
    }
    @Override
    public String toString() {
        return "CNPJ: " + cnpj + " | Endereço: " + endereco.getLogradouro() + ", " + endereco.getNumero() + " | CEP: " + endereco.getCep() + " | Cidade: " + endereco.getCidade() + " | UF: " + endereco.getUf() + " | Motoristas: " + motoristas + " | Encomendas: " + encomendas;
    }
}
