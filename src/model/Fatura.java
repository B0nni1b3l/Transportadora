package model;

import interfaces.Calculavel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Fatura implements Calculavel {

    private String codigoFatura;
    private Encomenda encomenda;
    private Rota rota;
    private double valorTotal;
    private LocalDate dataEmissao;
    private LocalDate dataVencimento;
    private boolean paga;

    public Fatura(String codigoFatura, Encomenda encomenda, Rota rota) {
        setCodigoFatura(codigoFatura);
        setEncomenda(encomenda);
        setRota(rota);
        this.dataEmissao = LocalDate.now();
        this.dataVencimento = dataEmissao.plusDays(7);
        this.valorTotal = calcularFrete(encomenda.getPeso(), rota.getDistanciaKm());
        this.paga = false;
    }

    public String getCodigoFatura() {
        return codigoFatura;
    }

    public void setCodigoFatura(String codigoFatura) {
        if (codigoFatura == null || codigoFatura.isBlank()) {
            throw new IllegalArgumentException("Codigo da fatura invalido");
        }
        this.codigoFatura = codigoFatura.trim().toUpperCase();
    }

    public Encomenda getEncomenda() {
        return encomenda;
    }

    public void setEncomenda(Encomenda encomenda) {
        if (encomenda == null) {
            throw new IllegalArgumentException("Encomenda invalida");
        }
        this.encomenda = encomenda;
    }

    public Rota getRota() {
        return rota;
    }

    public void setRota(Rota rota) {
        if (rota == null) {
            throw new IllegalArgumentException("Rota invalida");
        }
        this.rota = rota;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public boolean isPaga() {
        return paga;
    }

    public void setPaga(boolean paga) {
        this.paga = paga;
    }

    @Override
    public double calcularFrete(double peso, double distancia) {
        return (peso * 1.5) + (distancia * 0.75);
    }

    public String toCsv() {
        return String.format("%s;%s;%.2f;%s;%s;%s", codigoFatura, encomenda.getCodigoRastreio(), valorTotal, dataEmissao, dataVencimento, paga);
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Fatura: " + codigoFatura + " | Encomenda: " + encomenda.getCodigoRastreio() + " | Valor: R$ " + String.format("%.2f", valorTotal) + " | Emissao: " + dataEmissao.format(fmt) + " | Vencimento: " + dataVencimento.format(fmt) + " | Paga: " + (paga ? "Sim" : "Nao");
    }
}
