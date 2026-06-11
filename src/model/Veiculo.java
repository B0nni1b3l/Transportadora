package model;

public abstract class Veiculo implements Persistivel {

    private String placa;
    private double capacidadeDeKg;
    private String disponivel;

    public Veiculo(String placa, double capacidadeDeKg, String disponivel) {
        setPlaca(placa);
        setCapacidadeDeKg(capacidadeDeKg);
        setDisponivel(disponivel);
    }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) {
        if (placa == null || placa.isBlank())
            throw new IllegalArgumentException("Placa não pode ser vazia.");
        this.placa = placa.toUpperCase().trim();
    }

    public double getCapacidadeDeKg() { return capacidadeDeKg; }
    public void setCapacidadeDeKg(double capacidadeDeKg) {
        if (capacidadeDeKg <= 0)
            throw new IllegalArgumentException("Capacidade deve ser maior que zero.");
        this.capacidadeDeKg = capacidadeDeKg;
    }

    public String getDisponivel() { return disponivel; }
    public void setDisponivel(String disponivel) {
        if (disponivel == null || disponivel.isBlank())
            throw new IllegalArgumentException("Disponibilidade não pode ser vazia.");
        this.disponivel = disponivel.trim(); // CORRIGIDO: era this.placa por engano
    }

    public abstract double calcularCapacidade();
    public abstract String getTipoVeiculo();

    @Override
    public String toString() {
        return String.format("[%s] Placa: %s | Capacidade: %.1f kg | Disponível: %s",
                getTipoVeiculo(), placa, capacidadeDeKg, disponivel);
    }
}