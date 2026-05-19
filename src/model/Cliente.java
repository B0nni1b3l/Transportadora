package model;

public class Cliente extends Pessoa{
    private TipoCliente tipoCliente; //aqui eu vou colocar no enum depois
    public Cliente(String nome, String cpf, String telefone, String email, TipoCliente tipoCliente) {
        super(nome, cpf, telefone, email);
        this.tipoCliente = tipoCliente;
    }
    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }
    @Override
    public void exibirDados(){
        System.out.println("===Cliente===");
        System.out.println(toString());
    }
    @Override
    public String toString(){
        return super.toString() + "| Tipo: " + tipoCliente;
    }
}
