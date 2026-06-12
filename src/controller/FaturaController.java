package controller;

import enums.UF;
import exception.EncomendaNaoEncontradaException;
import exception.FaturaJaEmitidaException;
import model.Encomenda;
import model.Endereco;
import model.Fatura;
import model.Filial;
import model.Rota;
import util.ArquivoUtil;
import util.LogUtil;
import view.FaturaView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FaturaController {

    private static final String ARQUIVO_TXT = "faturas.txt";
    private static final String ARQUIVO_CSV = "faturas.csv";
    private static final Logger logger = LogUtil.getLogger();

    private final List<Fatura> faturas = new ArrayList<>();
    private final FaturaView view;
    private final EncomendaController encomendaController;

    public FaturaController(FaturaView view, EncomendaController encomendaController) {
        this.view = view;
        this.encomendaController = encomendaController;
    }

    public void emitirFatura() {
        try {
            String codigoEncomenda = view.lerCodigoEncomenda();
            Encomenda encomenda = encomendaController.buscarPorCodigo(codigoEncomenda);

            validarFaturaDuplicada(codigoEncomenda);

            String codigoFatura = view.lerCodigoFatura();
            double distanciaKm = view.lerDistancia();

            Filial origem = new Filial("00000000000100",
                    new Endereco("Rua A", "100", "80000-000", "Curitiba", UF.PR));

            Filial destino = new Filial("00000000000200",
                    new Endereco("Rua B", "200", "01000-000", "Sao Paulo", UF.SP));

            Rota rota = new Rota(origem, destino, distanciaKm, 3);

            Fatura fatura = new Fatura(codigoFatura, encomenda, rota);
            faturas.add(fatura);

            salvarEmArquivo();

            view.mostrarMsg("Fatura emitida com sucesso");
            view.mostrarMsg(fatura.toString());
            logger.info("Fatura emitida: " + codigoFatura);

        } catch (EncomendaNaoEncontradaException | FaturaJaEmitidaException e) {
            view.mostrarMsg("Erro: " + e.getMessage());
        } catch (IllegalArgumentException | IOException e) {
            view.mostrarMsg("Erro: " + e.getMessage());
        }
    }

    public void listarFaturas() {
        if (faturas.isEmpty()) {
            view.mostrarMsg("Nenhuma fatura emitida");
            return;
        }

        for (Fatura f : faturas) {
            view.mostrarMsg(f.toString());
        }
    }

    public void listarFaturasEmAberto() {
        boolean encontrou = false;

        for (Fatura f : faturas) {
            if (!f.isPaga()) {
                view.mostrarMsg(f.toString());
                encontrou = true;
            }
        }

        if (!encontrou) {
            view.mostrarMsg("Nao ha faturas em aberto");
        }
    }

    public void marcarComoPaga() {
        String codigoFatura = view.lerCodigoFatura();

        for (Fatura f : faturas) {
            if (f.getCodigoFatura().equalsIgnoreCase(codigoFatura)) {
                f.setPaga(true);

                try {
                    salvarEmArquivo();
                } catch (IOException e) {
                    view.mostrarMsg("Erro ao salvar arquivo");
                }

                view.mostrarMsg("Fatura marcada como paga");
                return;
            }
        }

        view.mostrarMsg("Fatura nao encontrada");
    }

    private void validarFaturaDuplicada(String codigoEncomenda) throws FaturaJaEmitidaException {
        for (Fatura f : faturas) {
            if (f.getEncomenda().getCodigoRastreio().equalsIgnoreCase(codigoEncomenda)) {
                throw new FaturaJaEmitidaException(codigoEncomenda);
            }
        }
    }

    private void salvarEmArquivo() throws IOException {
        List<String> linhasTxt = new ArrayList<>();
        List<String> linhasCsv = new ArrayList<>();

        linhasTxt.add("=========== FATURAS ===========");
        for (Fatura f : faturas) {
            linhasTxt.add(f.toString());
            linhasCsv.add(f.toCsv());
        }
        linhasTxt.add("===============================");

        ArquivoUtil.gravar(ARQUIVO_TXT, linhasTxt);
        ArquivoUtil.gravar(ARQUIVO_CSV, linhasCsv);
    }

    public void iniciar() {
        int op;
        do {
            view.menu();
            op = view.lerOp();

            switch (op) {
                case 1 -> emitirFatura();
                case 2 -> listarFaturas();
                case 3 -> listarFaturasEmAberto();
                case 4 -> marcarComoPaga();
                case 5 -> view.mostrarMsg("Voltando ao menu principal");
                default -> view.mostrarMsg("Opcao invalida");
            }
        } while (op != 5);
    }
}