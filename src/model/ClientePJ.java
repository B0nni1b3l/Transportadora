package model;

import enums.TipoCliente;

public class ClientePJ extends Cliente{
    private String cnpj;
    private String nomeEmpresa;

    public ClientePJ(String nome, String cpf, String telefone, String email, TipoCliente tipoCliente, String cnpj, String nomeEmpresa) {
        super(nome, cpf, telefone, email, TipoCliente.PJ);
        setCnpj(cnpj);
        setNomeEmpresa(nomeEmpresa);
    }
    public String getNomeEmpresa() {
        return nomeEmpresa;
    }
    public void setNomeEmpresa(String nomeEmpresa) {
        if(!nomeEmpresa.isEmpty()){
            this.nomeEmpresa = nomeEmpresa;
        }
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        if(cnpj.length()==14 && cnpj.matches("\\d{14}")) {
            this.cnpj = cnpj;
        }
    }
    @Override
    public void exibirDados() {
        System.out.println("===Cliente PJ===");
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return super.toString() + "CNPJ: " + cnpj + " | Nome da Empresa: " + nomeEmpresa;
    }
}
