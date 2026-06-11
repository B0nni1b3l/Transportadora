package util;
import model.Pessoa;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ClienteRepositorio implements Repositorio<Pessoa> {
    private static final Logger logger = Logger.getLogger(ClienteRepositorio.class.getName());
    private static final String CAMINHO_ARQUIVO = "clientes.txt";
    @Override
    public String getCaminhoArquivo() {
        return CAMINHO_ARQUIVO;
    }
    @Override
    public void salvar(ArrayList<Pessoa> lista, String titulo) throws Exception {
        logger.info("Iniciando gravação do arquivo de clientes: " + CAMINHO_ARQUIVO);

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
            if (lista.isEmpty()) {
                escritor.write("Nenhum cliente cadastrado.");
                logger.warning("Lista de clientes está vazia. Arquivo gravado sem registros.");
            } else {
                escritor.write("\t=====" + titulo + "=====\n");
                for (Pessoa p : lista) {
                    escritor.write(p.toString());
                    escritor.newLine();
                }
                logger.info("Arquivo de clientes gravado com sucesso. Total de registros: " + lista.size());
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao gravar arquivo de clientes: " + CAMINHO_ARQUIVO, e);
            throw e;
        }
    }
}
