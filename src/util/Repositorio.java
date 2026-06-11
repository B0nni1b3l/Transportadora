package util;
import java.util.ArrayList;
public interface Repositorio<T> {
    void salvar(ArrayList<T> lista, String titulo) throws Exception;
    String getCaminhoArquivo();
}
