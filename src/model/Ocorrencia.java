package model;

import exceptions.OcorrenciaInvalidaException;
import java.io.Serializable;
import java.time.LocalDateTime;

    public class Ocorrencia implements Serializable {
        private final int idOcorrencia;
        private final String codigoEncomenda;
        private final String descricao;
        private final LocalDateTime dataHora;
        private boolean resolvido;

        public Ocorrencia(int idOcorrencia, String codigoEncomenda, String descricao) throws OcorrenciaInvalidaException {
            if (codigoEncomenda == null || codigoEncomenda.trim().isEmpty()) {
                throw new OcorrenciaInvalidaException("O código da encomenda não pode ser vazio.");
            }
            if (descricao == null || descricao.trim().isEmpty()) {
                throw new OcorrenciaInvalidaException("A descrição da ocorrência não pode ser vazia.");
            }

            this.idOcorrencia = idOcorrencia;
            this.codigoEncomenda = codigoEncomenda;
            this.descricao = descricao;
            this.dataHora = LocalDateTime.now();
            this.resolvido = false;
        }

        public int getIdOcorrencia() { return idOcorrencia; }
        public String getCodigoEncomenda() { return codigoEncomenda; }
        public String getDescricao() { return descricao; }
        public LocalDateTime getDataHora() { return dataHora; }
        public boolean isResolvido() { return resolvido; }

        public void setResolvido(boolean resolvido) {
            this.resolvido = resolvido;
        }

        @Override
        public String toString() {
            return "ID: " + getIdOcorrencia() +
                    " | Encomenda: " + getCodigoEncomenda() +
                    " | Descrição: " + getDescricao() +
                    " | Data: " + getDataHora() +
                    " | Status: " + (isResolvido() ? "RESOLVIDO" : "ATIVA");
        }
    }
