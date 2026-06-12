package exception;

public class FaturaJaEmitidaException extends Exception {
    public FaturaJaEmitidaException(String codigoEncomenda) {
        super("Ja existe uma fatura emitida para esta encomenda: " + codigoEncomenda);
    }
}
