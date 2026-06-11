package model;

import interfaces.Calculavel;

public class Rota implements Calculavel {

    private Filial filialOrigem;
    private Filial filialDestino;

    private double distanciaKm;
    private int prazoDias;

    public Rota(Filial filialOrigem, Filial filialDestino,
                double distanciaKm, int prazoDias) {

        setFilialOrigem(filialOrigem);
        setFilialDestino(filialDestino);
        setDistanciaKm(distanciaKm);
        setPrazoDias(prazoDias);
    }

    public Filial getFilialOrigem() {
        return filialOrigem;
    }

    public void setFilialOrigem(Filial filialOrigem) {
        this.filialOrigem = filialOrigem;
    }

    public Filial getFilialDestino() {
        return filialDestino;
    }

    public void setFilialDestino(Filial filialDestino) {
        this.filialDestino = filialDestino;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {

        if (distanciaKm <= 0) {
            System.out.println("Distância inválida.");
        } else {
            this.distanciaKm = distanciaKm;
        }
    }

    public int getPrazoDias() {
        return prazoDias;
    }

    public void setPrazoDias(int prazoDias) {

        if (prazoDias <= 0) {
            System.out.println("Prazo inválido.");
        } else {
            this.prazoDias = prazoDias;
        }
    }

    @Override
    public double calcularFrete(double peso, double distancia) {

        return (peso * 0.8) + (distancia * 0.5);
    }

    public void exibirDados() {

        System.out.println("Origem: " +
                filialOrigem.getCnpj());

        System.out.println("Destino: " +
                filialDestino.getCnpj());

        System.out.println("Distância: " +
                distanciaKm + " km");

        System.out.println("Prazo: " +
                prazoDias + " dias");
    }
    @Override
    public String toString() {

        return "Origem: " + filialOrigem.getCnpj() + " | Destino: " + filialDestino.getCnpj() + " | Distância: " + distanciaKm + " km" + " | Prazo: " + prazoDias + " dias";
    }
}
