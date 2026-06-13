package util;

import model.Rota;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RotaRepositorio implements Repositorio<Rota> {

    private static final Logger logger =
            Logger.getLogger(RotaRepositorio.class.getName());

    private static final String CAMINHO_ARQUIVO = "rotas.txt";

    @Override
    public String getCaminhoArquivo() {
        return CAMINHO_ARQUIVO;
    }

    @Override
    public void salvar(ArrayList<Rota> lista, String titulo) throws Exception {

        logger.info("Iniciando gravação do arquivo de rotas: " + CAMINHO_ARQUIVO);

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {

            if (lista.isEmpty()) {

                escritor.write("Nenhuma rota cadastrada.");
                logger.warning("Lista de rotas está vazia. Arquivo gravado sem registros.");

            } else {

                escritor.write("\t=====" + titulo + "=====\n");

                for (Rota rota : lista) {
                    escritor.write(rota.toString());
                    escritor.newLine();
                }

                logger.info("Arquivo de rotas gravado com sucesso. Total de registros: " + lista.size());
            }

        } catch (Exception e) {

            logger.log(Level.SEVERE, "Erro ao gravar arquivo de rotas: " + CAMINHO_ARQUIVO, e);
            throw e;
        }
    }
}
