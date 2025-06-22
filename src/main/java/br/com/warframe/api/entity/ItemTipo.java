package br.com.warframe.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ItemTipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemTipoId;

    // Getters e setters
    public Long getItemTipoId() {
        return itemTipoId;
    }

    public void setItemTipoId(Long itemTipoId) {
        this.itemTipoId = itemTipoId;
    }
}