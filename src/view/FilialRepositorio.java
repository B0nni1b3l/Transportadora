package util;

import model.Filial;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FilialRepositorio implements Repositorio<Filial> {

    private static final Logger logger =
            Logger.getLogger(FilialRepositorio.class.getName());

    private static final String CAMINHO_ARQUIVO = "filiais.txt";

    @Override
    public String getCaminhoArquivo() {
        return CAMINHO_ARQUIVO;
    }

    @Override
    public void salvar(ArrayList<Filial> lista, String titulo) throws Exception {

        logger.info("Iniciando gravação do arquivo de filiais: " + CAMINHO_ARQUIVO);

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {

            if (lista.isEmpty()) {

                escritor.write("Nenhuma filial cadastrada.");
                logger.warning("Lista de filiais está vazia. Arquivo gravado sem registros.");

            } else {

                escritor.write("\t=====" + titulo + "=====\n");

                for (Filial filial : lista) {
                    escritor.write(filial.toString());
                    escritor.newLine();
                }

                logger.info("Arquivo de filiais gravado com sucesso. Total de registros: " + lista.size());
            }

        } catch (Exception e) {

            logger.log(Level.SEVERE, "Erro ao gravar arquivo de filiais: " + CAMINHO_ARQUIVO, e);
            throw e;
        }
    }
}
