package controller;

import enums.EventoRastreamento;
import exception.EncomendaNaoEncontradaException;
import model.Encomenda;
import model.Rastreamento;
import util.ArquivoUtil;
import util.LogUtil;
import view.RastreamentoView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class RastreamentoController {

    private static final String ARQUIVO_TXT = "rastreamentos.txt";
    private static final Logger logger = LogUtil.getLogger();

    private final List<Rastreamento> rastreamentos = new ArrayList<>();
    private final RastreamentoView view;
    private final EncomendaController encomendaController;

    public RastreamentoController(RastreamentoView view, EncomendaController encomendaController) {
        this.view = view;
        this.encomendaController = encomendaController;
    }

    public void registrarEvento() {
        try {
            String codigo = view.lerCodigoEncomenda();
            Encomenda encomenda = encomendaController.buscarPorCodigo(codigo);

            Rastreamento rastreamento = buscarOuCriar(codigo);
            String localizacao = view.lerLocalizacao();
            EventoRastreamento evento = view.lerEvento();

            rastreamento.registrarEvento(localizacao, evento);
            salvarEmArquivo();

            view.mostrarMsg("Evento registrado com sucesso para a encomenda " + encomenda.getCodigoRastreio());
            logger.info("Evento registrado para encomenda: " + codigo);

        } catch (EncomendaNaoEncontradaException e) {
            view.mostrarMsg("Erro: " + e.getMessage());
        } catch (IllegalArgumentException | IOException e) {
            view.mostrarMsg("Erro: " + e.getMessage());
        }
    }

    public void buscarHistorico() {
        String codigo = view.lerCodigoEncomenda();

        for (Rastreamento r : rastreamentos) {
            if (r.getCodigoEncomenda().equalsIgnoreCase(codigo)) {
                view.mostrarMsg(r.toString());
                return;
            }
        }

        view.mostrarMsg("Nenhum historico encontrado para esse codigo");
    }

    private Rastreamento buscarOuCriar(String codigo) {
        for (Rastreamento r : rastreamentos) {
            if (r.getCodigoEncomenda().equalsIgnoreCase(codigo)) {
                return r;
            }
        }

        Rastreamento novo = new Rastreamento(codigo);
        rastreamentos.add(novo);
        return novo;
    }

    private void salvarEmArquivo() throws IOException {
        List<String> linhas = new ArrayList<>();

        for (Rastreamento r : rastreamentos) {
            linhas.add("ENCOMENDA: " + r.getCodigoEncomenda());
            linhas.addAll(r.getHistoricoEventos());
            linhas.add("----------------------------------");
        }

        ArquivoUtil.gravar(ARQUIVO_TXT, linhas);
    }

    public void iniciar() {
        int op;
        do {
            view.menu();
            op = view.lerOp();

            switch (op) {
                case 1 -> registrarEvento();
                case 2 -> buscarHistorico();
                case 3 -> view.mostrarMsg("Voltando ao menu principal");
                default -> view.mostrarMsg("Opcao invalida");
            }
        } while (op != 3);
    }
}
