package controller;

import exceptions.OcorrenciaInvalidaException;
import model.Ocorrencia;
import util.LoggerService;

import java.util.ArrayList;

public class OcorrenciaController {
    private final ArrayList<Ocorrencia> listaOcorrencias;
    private int proximoId;

    public OcorrenciaController() {
        this.listaOcorrencias = new ArrayList<>();
        atualizarProximoId();
    }

    private void atualizarProximoId() {
        proximoId = 1;
        for (Ocorrencia o : listaOcorrencias) {
            if (o.getIdOcorrencia() >= proximoId) {
                proximoId = o.getIdOcorrencia() + 1;
            }
        }
    }

    public void registrarOcorrencia(String codigoEncomenda, String descricao) throws OcorrenciaInvalidaException {

        Ocorrencia ocorrencia = new Ocorrencia(proximoId, codigoEncomenda, descricao);

        listaOcorrencias.add(ocorrencia);
        LoggerService.log("INFO", "Ocorrência registrada com ID: " + proximoId);
        proximoId++;
    }

    public void iniciarDadosOcorrencia() {
        try {
            if (listaOcorrencias.isEmpty()) {
                registrarOcorrencia("ENC123", "Pneu furado do caminhão na BR-277.");
                registrarOcorrencia("ENC456", "Destinatário ausente na entrega urbana.");
            }
        } catch (OcorrenciaInvalidaException e) {
            LoggerService.log("ERROR", "Falha ao iniciar dados automáticos: " + e.getMessage());
        }
    }

    public ArrayList<Ocorrencia> listarTodas() {
        return listaOcorrencias;
    }

    public boolean resolverOcorrencia(int id) {
        for (Ocorrencia o : listaOcorrencias) {
            if (o.getIdOcorrencia() == id) {
                o.setResolvido(true);
                LoggerService.log("INFO", "Ocorrência resolvida para o ID: " + id);
                return true;
            }
        }
        LoggerService.log("WARNING", "Tentativa de resolver Ocorrência inexistente com ID: " + id);
        return false;
    }
}
