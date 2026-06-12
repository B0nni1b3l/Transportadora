package controller;

import enums.TipoChamado;
import exceptions.ChamadoInexistenteException;
import model.Suporte;
import util.LoggerService;

import java.util.HashMap;
import java.util.Map;

public class SuporteController {

    private final Map<Integer, Suporte> chamados;
    private int proximoId;

    public SuporteController() {
        this.chamados = new HashMap<>();
        atualizarProximoId();
    }

    private void atualizarProximoId() {
        proximoId = 1;
        for (Integer id : chamados.keySet()) {
            if (id >= proximoId) {
                proximoId = id + 1;
            }
        }
    }

    public void abrirChamado(String codigoEncomenda, String cpfCliente, TipoChamado tipo, String mensagem) {
        Suporte chamado = new Suporte(proximoId, codigoEncomenda, cpfCliente, tipo, mensagem);

        chamados.put(proximoId, chamado);
        LoggerService.log("INFO", "Chamado de suporte aberto com ID: " + proximoId);
        proximoId++;
    }

    public void iniciarDadosSuporte() {
        if (chamados.isEmpty()) {
            abrirChamado("ENC123", "123.456.789-00", TipoChamado.ATRASO_ENTREGA, "Carga retida devido ao pneu furado.");
        }
    }

    public Map<Integer, Suporte> listarTodos() {
        return chamados;
    }

    public boolean atualizarStatusChamado(int id, String novoStatus) throws ChamadoInexistenteException {
        Suporte chamado = chamados.get(id);

        if (chamado == null) {
            LoggerService.log("WARNING", "Tentativa de atualizar chamado inexistente: " + id);
            throw new ChamadoInexistenteException("O ID informado não corresponde a nenhum chamado aberto.");
        }

        chamado.setStatus(novoStatus);
        LoggerService.log("INFO", "Status do chamado ID " + id + " alterado para: " + novoStatus);
        return true;
    }
}
