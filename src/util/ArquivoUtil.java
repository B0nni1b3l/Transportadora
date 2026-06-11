package util;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtil {

    private static final String PASTA = "dados/";

    static {
        new File(PASTA).mkdirs();
    }

    public static void gravar(String nomeArquivo, List<String> linhas) throws IOException {
        Path caminho = Paths.get(PASTA + nomeArquivo);
        try (BufferedWriter bw = Files.newBufferedWriter(caminho)) {
            for (String linha : linhas) {
                bw.write(linha);
                bw.newLine();
            }
        }
    }

    public static List<String> ler(String nomeArquivo) throws IOException {
        Path caminho = Paths.get(PASTA + nomeArquivo);
        if (!Files.exists(caminho)) return new ArrayList<>();
        return Files.readAllLines(caminho);
    }
}