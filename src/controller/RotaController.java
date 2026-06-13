package controller;

import model.Rota;
import util.Repositorio;
import util.RotaRepositorio;
import view.RotaView;

import java.util.ArrayList;
import java.util.logging.Logger;

public class RotaController {

    private static final Logger logger =
            Logger.getLogger(RotaController.class.getName());

    private ArrayList<Rota> rotas;
    private RotaView view;
    private Repositorio<Rota> repositorio;

    public RotaController(RotaView view) {
        this.rotas = new ArrayList<>();
        this.view = view;
        this.repositorio = new RotaRepositorio();

        logger.info("RotaController inicializado.");
    }

    public RotaController(RotaView view, Repositorio<Rota> repositorio) {
        this.rotas = new ArrayList<>();
        this.view = view;
        this.repositorio = repositorio;

        logger.info("RotaController inicializado com repositório customizado.");
    }

    public void cadastrar(Rota rota) {
        rotas.add(rota);

        try {

            repositorio.salvar(rotas, "Cadastro de Rotas");

        } catch (Exception e) {

            logger.warning("Erro ao salvar rotas.");
        }
        logger.info("Rota cadastrada: "
                + rota.getFilialOrigem().getCnpj()
                + " -> "
                + rota.getFilialDestino().getCnpj());
    }
    public ArrayList<Rota> listar() {
        return rotas;
    }
    public Rota buscarPorFiliais(String cnpjOrigem, String cnpjDestino) {

        for (Rota rota : rotas) {
            if (rota.getFilialOrigem().getCnpj().equals(cnpjOrigem)
                    && rota.getFilialDestino().getCnpj().equals(cnpjDestino)) {
                return rota;
            }
        }
        logger.warning("Rota não encontrada: " + cnpjOrigem + " -> " + cnpjDestino);

        return null;
    }
    public boolean remover(String cnpjOrigem, String cnpjDestino) {

        Rota rota = buscarPorFiliais(cnpjOrigem, cnpjDestino);

        if (rota == null) {
            logger.warning("Não foi possível remover. Rota não encontrada.");
            return false;
        } else {
            rotas.remove(rota);

            try {

                repositorio.salvar(rotas, "Cadastro de Rotas");

            } catch (Exception e) {

                logger.warning("Erro ao salvar rotas.");
            }

            logger.info("Rota removida: " + cnpjOrigem + " -> " + cnpjDestino);

            return true;
        }
    }

    public double calcularFrete(Rota rota, double peso) {

        double valor = rota.calcularFrete(peso, rota.getDistanciaKm());

        logger.info("Frete calculado no valor de R$ " + valor);

        return valor;
    }
}
