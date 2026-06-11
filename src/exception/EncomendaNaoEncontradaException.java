package exception;

public class EncomendaNaoEncontradaException extends Exception {
    public EncomendaNaoEncontradaException(String codigo) {
        super("Encomenda com código '" + codigo + "' não encontrada.");
    }
}