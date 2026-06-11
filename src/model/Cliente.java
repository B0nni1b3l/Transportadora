package model;

import enums.TipoCliente;

public class Cliente extends Pessoa{
    private TipoCliente tipoCliente;
    public Cliente(String nome, String cpf, String telefone, String email, TipoCliente tipoCliente) {
        super(nome, cpf, telefone, email);
        this.tipoCliente = tipoCliente;
    }
    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }
    @Override
    public void exibirDados(){
        System.out.println(toString());
    }
    @Override
    public String toString(){
        return super.toString() + "| Tipo: " + tipoCliente;
    }
}
