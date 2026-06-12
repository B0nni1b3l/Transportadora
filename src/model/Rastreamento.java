package model;

import enums.EventoRastreamento;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Rastreamento {

    private String codigoEncomenda;
    private List<String> historicoEventos;

    public Rastreamento(String codigoEncomenda) {
        setCodigoEncomenda(codigoEncomenda);
        this.historicoEventos = new ArrayList<>();
    }

    public String getCodigoEncomenda() {
        return codigoEncomenda;
    }

    public void setCodigoEncomenda(String codigoEncomenda) {
        if (codigoEncomenda == null || codigoEncomenda.isBlank()) {
            throw new IllegalArgumentException("Codigo da encomenda invalido");
        }
        this.codigoEncomenda = codigoEncomenda.trim().toUpperCase();
    }

    public List<String> getHistoricoEventos() {
        return historicoEventos;
    }

    public void registrarEvento(String localizacao, EventoRastreamento evento) {
        if (localizacao == null || localizacao.isBlank()) {
            throw new IllegalArgumentException("Localizacao invalida");
        }
        if (evento == null) {
            throw new IllegalArgumentException("Evento invalido");
        }

        String dataHora = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        String registro = dataHora + " | " + localizacao.trim() + " | " + evento.getDescricao();
        historicoEventos.add(registro);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Codigo da encomenda: ").append(codigoEncomenda).append("\n");

        if (historicoEventos.isEmpty()) {
            sb.append("Nenhum evento registrado");
        } else {
            for (String evento : historicoEventos) {
                sb.append(evento).append("\n");
            }
        }

        return sb.toString();
    }
}