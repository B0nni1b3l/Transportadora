package model;

import enums.TipoChamado;
import java.io.Serializable;

public class Suporte implements Serializable {
    private final int idChamado;
    private final String codigoEncomenda;
    private final String cpfCliente;
    private final TipoChamado tipo;
    private final String mensagem;
    private String status;

    public Suporte(int idChamado, String codigoEncomenda, String cpfCliente, TipoChamado tipo, String mensagem) {
        this.idChamado = idChamado;
        this.codigoEncomenda = codigoEncomenda;
        this.cpfCliente = cpfCliente;
        this.tipo = tipo;
        this.mensagem = mensagem;
        this.status = "ABERTO";
    }

    public int getIdChamado() { return idChamado; }
    public String getCodigoEncomenda() { return codigoEncomenda; }
    public String getCpfCliente() { return cpfCliente; }
    public TipoChamado getTipo() { return tipo; }
    public String getMensagem() { return mensagem; }
    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Chamado ID: " + getIdChamado() +
                " | Cliente CPF: " + getCpfCliente() +
                " | Encomenda: " + getCodigoEncomenda() +
                " | Tipo: " + getTipo() +
                " | Status: " + getStatus() +
                "\n  Mensagem: " + getMensagem();
    }
}
