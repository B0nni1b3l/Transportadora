package model;

public class Motorista extends Pessoa{
    private String numeroCNH;
    private String categoriaCNH;
    private boolean disponivel;

    public Motorista(String nome, String cpf, String telefone, String email, String numeroCNH, String categoriaCNH, boolean disponivel) {
        super(nome, cpf, telefone, email);
        setNumeroCNH(numeroCNH);
        this.categoriaCNH = categoriaCNH;
        this.disponivel = true;
    }
    public String getNumeroCNH() {
        return numeroCNH;
    }
    public void setNumeroCNH(String numeroCNH) {
        if(!numeroCNH.isEmpty()){
            this.numeroCNH = numeroCNH;
        }
    }
    public String getCategoriaCNH() {
        return categoriaCNH;
    }
    public boolean isDisponivel() {
        return disponivel;
    }
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    @Override
    public void exibirDados() {
        System.out.println(toString());
    }
    @Override
    public String toString() {
        return super.toString() + "Numero da CNH: " + numeroCNH + " | Categoria CNH: " + categoriaCNH + "| Está disponivel?" + ((disponivel)? " Sim": " Não");
    }
}
