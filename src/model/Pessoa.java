package model;
public abstract class Pessoa {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    public Pessoa(String nome, String cpf, String telefone, String email){
        setNome(nome);
        setCpf(cpf);
        setTelefone(telefone);
        setEmail(email);
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        if(!nome.isEmpty()){
            this.nome = nome;
        }
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        if(!telefone.isEmpty()){
            this.telefone = telefone;
        }
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        if(!email.isEmpty()){
            this.email = email;
        }
    }
    public void setCpf(String cpf) {
        if(!cpf.isEmpty()){
            this.cpf = cpf;
        }
    }
    public String getCpf(){
        return cpf;
    }
    public abstract void exibirDados();
    @Override
    public String toString(){
        return "\nNome: " + nome + " | CPF: " + cpf + " | Telefone: " + telefone + " | Email: " + email;
    }
}
