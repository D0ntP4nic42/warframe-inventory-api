package br.com.warframe.api.entity;

import jakarta.persistence.Entity;

@Entity
public class Warframe extends ItemTipo {

    private int nivelMinimo;

    public int getNivelMinimo() {
        return nivelMinimo;
    }

    public void setNivelMinimo(int nivelMinimo) {
        this.nivelMinimo = nivelMinimo;
    }
}