package model;

public class Encomenda implements Persistivel {

    private String codigoRastreio;
    private double peso;
    private String dimensoes;
    private StatusEncomenda status;

    /** COMPOSIÇÃO: Encomenda "tem um" Veiculo atribuído (pode ser null = sem veículo). */
    private Veiculo veiculoAtribuido;

    public Encomenda(String codigoRastreio, double peso, String dimensoes, StatusEncomenda status) {
        setCodigoRastreio(codigoRastreio);
        setPeso(peso);
        setDimensoes(dimensoes);
        setStatus(status);
    }

    public String getCodigoRastreio() { return codigoRastreio; }
    public void setCodigoRastreio(String codigoRastreio) {
        if (codigoRastreio == null || codigoRastreio.isBlank())
            throw new IllegalArgumentException("Código de rastreio não pode ser vazio.");
        this.codigoRastreio = codigoRastreio.toUpperCase().trim();
    }

    public double getPeso() { return peso; }
    public void setPeso(double peso) {
        if (peso <= 0)
            throw new IllegalArgumentException("Peso deve ser maior que zero.");
        this.peso = peso;
    }

    public String getDimensoes() { return dimensoes; }
    public void setDimensoes(String dimensoes) {
        if (dimensoes == null || dimensoes.isBlank())
            throw new IllegalArgumentException("Dimensões não podem ser vazias.");
        this.dimensoes = dimensoes.trim();
    }

    public StatusEncomenda getStatus() { return status; }
    public void setStatus(StatusEncomenda status) {
        if (status == null)
            throw new IllegalArgumentException("Status não pode ser nulo.");
        this.status = status;
    }

    public Veiculo getVeiculoAtribuido() { return veiculoAtribuido; }
    public void setVeiculoAtribuido(Veiculo veiculoAtribuido) {
        this.veiculoAtribuido = veiculoAtribuido;
    }

    @Override
    public String toCsv() {
        String placaVeiculo = (veiculoAtribuido != null) ? veiculoAtribuido.getPlaca() : "";
        return String.format("%s;%.2f;%s;%s;%s",
                codigoRastreio, peso, dimensoes, status.name(), placaVeiculo);
    }

    @Override
    public String toString() {
        String veiculo = (veiculoAtribuido != null)
                ? " | Veículo: " + veiculoAtribuido.getPlaca()
                : " | Sem veículo atribuído";
        return String.format("Cód: %s | Peso: %.1f kg | Dim: %s | Status: %s%s",
                codigoRastreio, peso, dimensoes, status.getDescricao(), veiculo);
    }
}