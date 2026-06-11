package util;
import model.Motorista;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class MotoristaRepositorio implements Repositorio<Motorista> {
    private static final Logger logger = Logger.getLogger(MotoristaRepositorio.class.getName());
    private static final String CAMINHO_ARQUIVO = "motoristas.txt";
    @Override
    public String getCaminhoArquivo() {
        return CAMINHO_ARQUIVO;
    }
    @Override
    public void salvar(ArrayList<Motorista> lista, String titulo) throws Exception {
        logger.info("Iniciando gravação do arquivo de motoristas: " + CAMINHO_ARQUIVO);
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
            if (lista.isEmpty()) {
                escritor.write("Nenhum motorista cadastrado.");
                logger.warning("Lista de motoristas está vazia. Arquivo gravado sem registros.");
            } else {
                escritor.write("\t=====" + titulo + "=====\n");
                for (Motorista m : lista) {
                    escritor.write(m.toString());
                    escritor.newLine();
                }
                logger.info("Arquivo de motoristas gravado com sucesso. Total de registros: " + lista.size());
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao gravar arquivo de motoristas: " + CAMINHO_ARQUIVO, e);
            throw e;
        }
    }
}

