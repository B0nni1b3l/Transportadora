package exception;

public class VeiculoNaoEncontradoException extends RuntimeException {
    public VeiculoNaoEncontradoException(String placa) {
        super("Veículo com placa: " + placa + " não encontrado.");
    }
}