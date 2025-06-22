package br.com.warframe.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.warframe.api.entity.Item;
import br.com.warframe.api.entity.User;
import br.com.warframe.api.entity.UserItem;
import br.com.warframe.api.repository.ItemRepository;
import br.com.warframe.api.repository.UserItemRepository;
import br.com.warframe.api.repository.UserRepository;
import br.com.warframe.api.vo.ItemDTO;

@Service
public class UserItemService {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final UserItemRepository userItemRepository;

    public UserItemService(UserRepository userRepository, ItemRepository itemRepository,
            UserItemRepository userItemRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.userItemRepository = userItemRepository;
    }

    @Transactional
    public void adicionarItemAoUsuario(String username, ItemDTO itemDTO, int quantidade) {
        User user = userRepository.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Item item = itemRepository.findByItemNome(itemDTO.getName())
                .orElseGet(() -> {
                    Item novoItem = new Item();
                    novoItem.setItemNome(itemDTO.getName());
                    novoItem.setItemDescricao(itemDTO.getDescription());
                    novoItem.setWikiaThumbnail(itemDTO.getWikiaThumbnail());

                    return itemRepository.save(novoItem);
                });

        UserItem userItem = userItemRepository.findByUserAndItem(user, item)
                .orElse(null);

        if (userItem != null) {
            userItem.setQuantidade(userItem.getQuantidade() + quantidade);
        } else {
            userItem = new UserItem();
            userItem.setUser(user);
            userItem.setItem(item);
            userItem.setQuantidade(quantidade);
        }

        userItemRepository.save(userItem);
    }
}
