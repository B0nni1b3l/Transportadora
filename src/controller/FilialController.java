package controller;

import model.Filial;
import util.FilialRepositorio;
import util.Repositorio;
import view.FilialView;

import java.util.ArrayList;
import java.util.logging.Logger;

public class FilialController {

    private static final Logger logger =
            Logger.getLogger(FilialController.class.getName());

    private ArrayList<Filial> filiais;
    private FilialView view;
    private Repositorio<Filial> repositorio;

    public FilialController(FilialView view) {
        this.filiais = new ArrayList<>();
        this.view = view;
        this.repositorio = new FilialRepositorio();

        logger.info("FilialController inicializado.");
    }

    public FilialController(FilialView view, Repositorio<Filial> repositorio) {
        this.filiais = new ArrayList<>();
        this.view = view;
        this.repositorio = repositorio;

        logger.info("FilialController inicializado com repositório customizado.");
    }

    public void cadastrar(Filial filial) {
        filiais.add(filial);

        try {

            repositorio.salvar(filiais, "Cadastro de Filiais");

        } catch (Exception e) {

            logger.warning("Erro ao salvar filiais.");
        }

        logger.info("Filial cadastrada: " + filial.getCnpj());
    }

    public ArrayList<Filial> listar() {
        return filiais;
    }

    public Filial buscarPorCnpj(String cnpj) {

        for (Filial filial : filiais) {
            if (filial.getCnpj().equals(cnpj)) {
                return filial;
            }
        }

        logger.warning("Filial não encontrada: " + cnpj);

        return null;
    }

    public boolean remover(String cnpj) {

        Filial filial = buscarPorCnpj(cnpj);

        if (filial == null) {
            logger.warning("Não foi possível remover. Filial não encontrada.");
            return false;
        } else {
            filiais.remove(filial);

            try {

                repositorio.salvar(filiais, "Cadastro de Filiais");

            } catch (Exception e) {

                logger.warning("Erro ao salvar filiais.");
            }

            logger.info("Filial removida: " + cnpj);

            return true;
        }
    }

    public boolean adicionarMotorista(String cnpj, String motorista) {

        Filial filial = buscarPorCnpj(cnpj);

        if (filial == null) {
            logger.warning("Não foi possível adicionar motorista. Filial não encontrada.");
            return false;
        } else {
            filial.adicionarMotorista(motorista);

            try {

                repositorio.salvar(filiais, "Cadastro de Filiais");

            } catch (Exception e) {

                logger.warning("Erro ao salvar filiais.");
            }

            logger.info("Motorista adicionado na filial: " + cnpj);

            return true;
        }
    }

    public boolean adicionarEncomenda(String cnpj, String encomenda) {

        Filial filial = buscarPorCnpj(cnpj);

        if (filial == null) {
            logger.warning("Não foi possível adicionar encomenda. Filial não encontrada.");
            return false;
        } else {
            filial.adicionarEncomenda(encomenda);

            try {

                repositorio.salvar(filiais, "Cadastro de Filiais");

            } catch (Exception e) {

                logger.warning("Erro ao salvar filiais.");
            }
            logger.info("Encomenda adicionada na filial: " + cnpj);

            return true;
        }
    }
}
