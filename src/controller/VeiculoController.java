package controller;

import exception.VeiculoNaoEncontradoException;
import model.*;
import util.ArquivoUtil;
import view.VeiculoView;
import java.util.logging.Logger;
import util.LogUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoController {

    private static final String ARQUIVO_CSV = "veiculos.csv";
    private static final String ARQUIVO_TXT = "veiculos.txt";
    private static final Logger logger = LogUtil.getLogger();
    private final List<Veiculo> veiculos = new ArrayList<>();
    private final VeiculoView view;

    public VeiculoController(VeiculoView view) {
        this.view = view;
        carregarDeArquivo();
    }

    public void cadastrar() {
        try {
            double capacidadeKg = view.lerCapacidadeKg();
            String disponivel = view.lerDisponivel();
            String placa = view.lerPlaca();
            int tipo = view.lerTipo();

            if (tipo == 1) {
                int numeroDeEixos = view.lerNumeroDeEixos();
                String tipoCacamba = view.lerTipoCacamba();
                Caminhao novoCaminhao = new Caminhao(placa, capacidadeKg, disponivel, numeroDeEixos, tipoCacamba);
                veiculos.add(novoCaminhao);
                salvarEmArquivo();
                view.mostrarMsg("Caminhão cadastrado com sucesso.");
                logger.info("Caminhão cadastrado: " + placa);
            } else if (tipo == 2) {
                String entregaUrbana = view.lerEntregaUrbana();
                Van novaVan = new Van(placa, capacidadeKg, disponivel, entregaUrbana);
                veiculos.add(novaVan);
                salvarEmArquivo();
                view.mostrarMsg("Van cadastrada com sucesso.");
                logger.info("Van cadastrada: " + placa);
            } else {
                view.mostrarMsg("Tipo inválido.");
            }
        } catch (IllegalArgumentException e) {
            view.mostrarMsg("Erro: " + e.getMessage());
        } catch (IOException e) {
            view.mostrarMsg("Erro ao salvar: " + e.getMessage());
        }
    }

    public void listar() {
        if (veiculos.isEmpty()) {
            view.mostrarMsg("Nenhum veículo cadastrado.");
            return;
        }
        view.mostrarMsg("====Veículos cadastrados====");
        for (int i = 0; i < veiculos.size(); i++) {
            view.mostrarMsg((i + 1) + ". " + veiculos.get(i).toString());
        }
        view.mostrarMsg("==============================");
    }

    public Veiculo buscarPorPlaca(String placa) {
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa.trim())) return v;
        }
        throw new VeiculoNaoEncontradoException(placa);
    }

    public void atualizarStatus() {
        try {
            String placa = view.lerPlaca();
            Veiculo v = buscarPorPlaca(placa);
            String novoStatus = view.lerDisponivel();
            v.setDisponivel(novoStatus);
            salvarEmArquivo();
            view.mostrarMsg("Status atualizado para: " + novoStatus);
            logger.info("Status do veículo " + placa + " alterado para " + novoStatus);
        } catch (VeiculoNaoEncontradoException e) {
            view.mostrarMsg("Erro: " + e.getMessage());
        } catch (IOException e) {
            view.mostrarMsg("Erro ao salvar: " + e.getMessage());
        }
    }

    public void excluirVeiculo() {
        try {
            String placa = view.lerPlaca();
            Veiculo veiculo = buscarPorPlaca(placa);
            veiculos.remove(veiculo);
            salvarEmArquivo();
            view.mostrarMsg("Veículo removido.");
            logger.info("Veículo removido: " + placa);
        } catch (VeiculoNaoEncontradoException e) {
            view.mostrarMsg("Erro: " + e.getMessage());
        } catch (IOException e) {
            view.mostrarMsg("Erro ao salvar: " + e.getMessage());
        }
    }

    public void verificarCapacidadeDisponivel() {
        try {
            String placa = view.lerPlaca();
            Veiculo veiculo = buscarPorPlaca(placa);
            if (!veiculo.getDisponivel().equalsIgnoreCase("Disponível")) {
                view.mostrarMsg("Veículo indisponível.");
            } else {
                double capacidade = veiculo.calcularCapacidade();
                view.mostrarMsg("Capacidade disponível: " + capacidade + " Kg.");
            }
        } catch (VeiculoNaoEncontradoException e) {
            view.mostrarMsg("Erro: " + e.getMessage());
        }
    }

    private void salvarEmArquivo() throws IOException {
        List<String> linhasCsv = new ArrayList<>();
        List<String> linhasTxt = new ArrayList<>();
        linhasTxt.add("========== LISTA DE VEÍCULOS ==========");
        for (Veiculo v : veiculos) {
            linhasCsv.add(v.toCsv());
            linhasTxt.add(v.toString());
        }
        linhasTxt.add("=======================================");
        ArquivoUtil.gravar(ARQUIVO_CSV, linhasCsv);
        ArquivoUtil.gravar(ARQUIVO_TXT, linhasTxt);
    }

    private void carregarDeArquivo() {
        try {
            List<String> linhas = ArquivoUtil.ler(ARQUIVO_CSV);
            for (String linha : linhas) {
                if (linha.isBlank()) continue;
                String[] p = linha.split(";", -1);
                // CAMINHAO;placa;capacidade;disponivel;eixos;tipoCacamba
                // VAN;placa;capacidade;disponivel;entregaUrbana
                if (p[0].equalsIgnoreCase("CAMINHAO")) {
                    veiculos.add(new Caminhao(
                            p[1],
                            Double.parseDouble(p[2].replace(",", ".")),
                            p[3],
                            Integer.parseInt(p[4]),
                            p[5]
                    ));
                } else if (p[0].equalsIgnoreCase("VAN")) {
                    veiculos.add(new Van(p[1], Double.parseDouble(p[2].replace(",", ".")), p[3], p[4]));
                }
            }
        } catch (IOException ignored) { }
    }

    public void iniciarVeiculo() {
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
                    excluirVeiculo();
                    break;
                case 5:
                    verificarCapacidadeDisponivel();
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