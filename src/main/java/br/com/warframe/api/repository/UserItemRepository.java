package br.com.warframe.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.warframe.api.entity.Item;
import br.com.warframe.api.entity.User;
import br.com.warframe.api.entity.UserItem;


public interface UserItemRepository extends JpaRepository<UserItem, Long> {
    Optional<UserItem> findByUserAndItem(User user, Item item);
}
