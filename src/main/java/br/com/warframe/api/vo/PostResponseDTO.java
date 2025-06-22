package br.com.warframe.api.vo;

import java.time.LocalDateTime;

public class PostResponseDTO {
    private Long id;
    private String conteudo;
    private String autorUsername;
    private LocalDateTime dataCriacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getAutorUsername() {
        return autorUsername;
    }

    public void setAutorUsername(String autorUsername) {
        this.autorUsername = autorUsername;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}