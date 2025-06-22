package br.com.warframe.api.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;

@Entity
public class Trocavel extends ItemTipo {

    private int nivelMinimo;

    private BigDecimal valorMinimo;

    public int getNivelMinimo() {
        return nivelMinimo;
    }

    public void setNivelMinimo(int nivelMinimo) {
        this.nivelMinimo = nivelMinimo;
    }

    public BigDecimal getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(BigDecimal valorMinimo) {
        this.valorMinimo = valorMinimo;
    }
}