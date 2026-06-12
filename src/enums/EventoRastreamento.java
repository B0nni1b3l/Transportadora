package enums;

public enum EventoRastreamento {
    POSTADO("Postado"),
    EM_TRANSFERENCIA("Em transferencia"),
    SAIU_PARA_ENTREGA("Saiu para entrega"),
    ENTREGUE("Entregue"),
    DEVOLVIDO("Devolvido");

    private final String descricao;

    EventoRastreamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
