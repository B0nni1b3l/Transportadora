package enums;

public enum StatusEncomenda {
    AGUARDANDO("Aguardando"),
    EM_TRANSITO("Em Trânsito"),
    ENTREGUE("Entregue"),
    DEVOLVIDA("Devolvida");

    private final String descricao;

    StatusEncomenda(String descricao) { this.descricao = descricao; }

    public String getDescricao() { return descricao; }

    @Override
    public String toString() { return descricao; }
}