package model;

public class Motorista extends Pessoa{
    private String numeroCNH;
    private String categoriaCNH;

    public Motorista(String nome, String cpf, String telefone, String email, String numeroCNH, String categoriaCNH) {
        super(nome, cpf, telefone, email);
        setNumeroCNH(numeroCNH);
        this.categoriaCNH = categoriaCNH;
    }
    public String getNumeroCNH() {
        return numeroCNH;
    }
    public void setNumeroCNH(String numeroCNH) {
        if(numeroCNH.length()==9 && numeroCNH.matches("\\d{9}")){
            this.numeroCNH = numeroCNH;
        }
    }
    public String getCategoriaCNH() {
        return categoriaCNH;
    }
    @Override
    public void exibirDados() {
        System.out.println("===Motorista===");
        System.out.println(toString());
    }
    @Override
    public String toString() {
        return super.toString() + "Numero da CNH: " + numeroCNH + " | Categoria CNH: " + categoriaCNH;
    }
}
