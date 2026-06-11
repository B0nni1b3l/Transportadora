package model;
import util.Transportavel;

public class Van extends Veiculo implements Transportavel {

    private String entregaUrbana;

    public Van(String placa, double capacidadeDeKg, String disponivel, String entregaUrbana) {
        super(placa, capacidadeDeKg, disponivel);
        setEntregaUrbana(entregaUrbana);
    }

    public String getEntregaUrbana() { return entregaUrbana; }
    public void setEntregaUrbana(String entregaUrbana) {
        if (entregaUrbana == null || entregaUrbana.isBlank())
            throw new IllegalArgumentException("Entrega urbana não pode ser vazia.");
        this.entregaUrbana = entregaUrbana.toUpperCase().trim();
    }

    @Override
    public double calcularCapacidade() {
        return entregaUrbana.equalsIgnoreCase("SIM") ? getCapacidadeDeKg() * 0.8 : getCapacidadeDeKg();
    }

    @Override
    public String getTipoVeiculo() { return "VAN"; }

    @Override
    public String toCsv() {
        return String.format("VAN;%s;%.2f;%s;%s",
                getPlaca(), getCapacidadeDeKg(), getDisponivel(), getEntregaUrbana());
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format(" | Entrega urbana: %s", getEntregaUrbana());
    }
}