package model;

public class Caminhao extends Veiculo implements Transportavel {

    private int numeroDeEixos;
    private String tipoCacamba;

    public Caminhao(String placa, double capacidadeDeKg, String disponivel, int numeroDeEixos, String tipoCacamba) {
        super(placa, capacidadeDeKg, disponivel);
        setNumeroDeEixos(numeroDeEixos);
        setTipoCacamba(tipoCacamba);
    }

    public int getNumeroDeEixos() { return numeroDeEixos; }
    public void setNumeroDeEixos(int numeroDeEixos) {
        if (numeroDeEixos < 2)
            throw new IllegalArgumentException("Caminhão precisa de pelo menos 2 eixos.");
        this.numeroDeEixos = numeroDeEixos;
    }

    public String getTipoCacamba() { return tipoCacamba; }
    public void setTipoCacamba(String tipoCacamba) {
        if (tipoCacamba == null || tipoCacamba.isBlank())
            throw new IllegalArgumentException("Tipo de caçamba não pode ser vazio.");
        this.tipoCacamba = tipoCacamba.trim();
    }

    @Override
    public double calcularCapacidade() {
        return getCapacidadeDeKg() * numeroDeEixos;
    }

    @Override
    public String getTipoVeiculo() { return "CAMINHÃO"; }

    @Override
    public String toCsv() {
        return String.format("CAMINHAO;%s;%.2f;%s;%d;%s",
                getPlaca(), getCapacidadeDeKg(), getDisponivel(), numeroDeEixos, tipoCacamba);
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format(" | Eixos: %d | Caçamba: %s", numeroDeEixos, tipoCacamba);
    }
}