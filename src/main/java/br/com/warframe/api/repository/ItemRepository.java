package br.com.warframe.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.warframe.api.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByItemNome(String itemName);

}
