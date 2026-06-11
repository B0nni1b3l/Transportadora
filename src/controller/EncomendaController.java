package controller;

import exception.EncomendaNaoEncontradaException;
import exception.VeiculoNaoEncontradoException;
import model.*;
import util.ArquivoUtil;
import view.EncomendaView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import util.LogUtil;
import enums.StatusEncomenda;

public class EncomendaController {

    private static final String ARQUIVO_CSV = "encomendas.csv";
    private static final String ARQUIVO_TXT = "encomendas.txt";
    private static final Logger logger = LogUtil.getLogger();
    private final List<Encomenda> encomendas = new ArrayList<>();
    private final EncomendaView view;
    private final VeiculoController veiculoController;

    public EncomendaController(EncomendaView view, VeiculoController veiculoController) {
        this.view = view;
        this.veiculoController = veiculoController;
        carregarDeArquivo();
    }

    public void cadastrar() {
        try {
            String codigo = view.lerCodigo();
            double peso = view.lerPeso();
            String dimensoes = view.lerDimensoes();
            StatusEncomenda status = view.lerStatus();

            Encomenda nova = new Encomenda(codigo, peso, dimensoes, status);
            encomendas.add(nova);
            salvarEmArquivo();
            view.mostrarMsg("Encomenda cadastrada com sucesso!");
            logger.info("Encomenda cadastrada: " + codigo);

        } catch (IllegalArgumentException e) {
            view.mostrarMsg("Erro de validação: " + e.getMessage());
        } catch (IOException e) {
            view.mostrarMsg("Erro ao salvar: " + e.getMessage());
        }
    }

    public void listar() {
        if (encomendas.isEmpty()) {
            view.mostrarMsg("Nenhuma encomenda cadastrada.");
            return;
        }
        view.mostrarMsg("\n====Lista de Encomendas====");
        for (int i = 0; i < encomendas.size(); i++) {
            view.mostrarMsg((i + 1) + ". " + encomendas.get(i).toString());
        }
        view.mostrarMsg("==============================");
    }

    public void atualizarStatus() {
        try {
            String codigo = view.lerCodigoBusca();
            Encomenda e = buscarPorCodigo(codigo);
            StatusEncomenda novoStatus = view.lerStatus();
            e.setStatus(novoStatus);
            salvarEmArquivo();
            view.mostrarMsg("Status atualizado para: " + novoStatus.getDescricao());
            logger.info("Status da encomenda " + codigo + " atualizado para " + novoStatus.getDescricao());
        } catch (EncomendaNaoEncontradaException e) {
            view.mostrarMsg("Erro: " + e.getMessage());
        } catch (IOException e) {
            view.mostrarMsg("Erro ao salvar: " + e.getMessage());
        }
    }

    public void atribuirVeiculo() {
        try {
            String codigoEncomenda = view.lerCodigoBusca();
            Encomenda encomenda = buscarPorCodigo(codigoEncomenda);

            view.mostrarMsg("Informe a placa do veículo:");
            String placa = view.lerPlacaVeiculo();
            Veiculo veiculo = veiculoController.buscarPorPlaca(placa);

            if (!"Disponível".equalsIgnoreCase(veiculo.getDisponivel())) {
                view.mostrarMsg("Veículo indisponível. Status atual: " + veiculo.getDisponivel() + " - operação cancelada.");
                return;
            }

            encomenda.setVeiculoAtribuido(veiculo);
            encomenda.setStatus(StatusEncomenda.EM_TRANSITO);
            veiculo.setDisponivel("Indisponível");

            salvarEmArquivo();
            view.mostrarMsg("Veículo " + placa + " atribuído à encomenda " + codigoEncomenda);
            logger.info("Veículo " + placa + " atribuído à encomenda " + codigoEncomenda);

        } catch (EncomendaNaoEncontradaException | VeiculoNaoEncontradoException e) {
            view.mostrarMsg("Erro: " + e.getMessage());
        } catch (IOException e) {
            view.mostrarMsg("Erro ao salvar: " + e.getMessage());
        }
    }

    public void excluir() {
        try {
            String codigo = view.lerCodigoBusca();
            Encomenda e = buscarPorCodigo(codigo);
            encomendas.remove(e);
            salvarEmArquivo();
            view.mostrarMsg("Encomenda removida.");
            logger.info("Encomenda removida: " + codigo);
        } catch (EncomendaNaoEncontradaException e) {
            view.mostrarMsg("Erro: " + e.getMessage());
        } catch (IOException e) {
            view.mostrarMsg("Erro ao salvar: " + e.getMessage());
        }
    }

    public Encomenda buscarPorCodigo(String codigo) throws EncomendaNaoEncontradaException {
        for (Encomenda e : encomendas) {
            if (e.getCodigoRastreio().equalsIgnoreCase(codigo.trim())) return e;
        }
        throw new EncomendaNaoEncontradaException(codigo);
    }

    private void salvarEmArquivo() throws IOException {
        List<String> linhasCsv = new ArrayList<>();
        List<String> linhasTxt = new ArrayList<>();
        linhasTxt.add("========== LISTA DE ENCOMENDAS ==========");
        for (Encomenda e : encomendas) {
            linhasCsv.add(e.toCsv());
            linhasTxt.add(e.toString());
        }
        linhasTxt.add("=========================================");
        ArquivoUtil.gravar(ARQUIVO_CSV, linhasCsv);
        ArquivoUtil.gravar(ARQUIVO_TXT, linhasTxt);
    }

    private void carregarDeArquivo() {
        try {
            List<String> linhas = ArquivoUtil.ler(ARQUIVO_CSV);
            for (String linha : linhas) {
                if (linha.isBlank()) continue;
                String[] p = linha.split(";", -1);
                StatusEncomenda st = StatusEncomenda.valueOf(p[3]);
                Encomenda e = new Encomenda(p[0], Double.parseDouble(p[1].replace(",", ".")), p[2], st);
                if (p.length > 4 && !p[4].isBlank()) {
                    try {
                        Veiculo v = veiculoController.buscarPorPlaca(p[4]);
                        e.setVeiculoAtribuido(v);
                    } catch (VeiculoNaoEncontradoException ignored) { }
                }
                encomendas.add(e);
            }
        } catch (IOException e) {
        }
    }

    public void iniciar() {
        int op;
        do {
            view.menu();
            op = view.lerOp();
            switch (op) {
                case 1:
                    cadastrar();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    atualizarStatus();
                    break;
                case 4:
                    atribuirVeiculo();
                    break;
                case 5:
                    excluir();
                    break;
                case 6:
                    view.mostrarMsg("Voltando ao menu principal...");
                    break;
                default:
                    view.mostrarMsg("Opção inválida.");
            }
        } while (op != 6);
    }
}