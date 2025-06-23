package br.com.warframe.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.warframe.api.entity.Item;
import br.com.warframe.api.entity.User;
import br.com.warframe.api.entity.UserItem;
import br.com.warframe.api.repository.ItemRepository;
import br.com.warframe.api.repository.UserItemRepository;
import br.com.warframe.api.repository.UserRepository;
import br.com.warframe.api.vo.ItemDTO;
import br.com.warframe.api.vo.UserItemResponseDTO;

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

    public List<UserItemResponseDTO> listarItensDoUsuario(String usernameOrEmail) {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<UserItem> userItems = userItemRepository.findByUser(user);

        return userItems.stream()
                .map(ui -> new UserItemResponseDTO(
                        ui.getItem().getItemId(),
                        ui.getItem().getItemNome(),
                        ui.getItem().getItemDescricao(),
                        ui.getItem().getWikiaThumbnail(),
                        ui.getQuantidade()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void atualizarQuantidade(String username, Long itemId, int novaQuantidade) {
        User user = userRepository.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        UserItem userItem = userItemRepository.findByUserAndItem(user, item)
                .orElseThrow(() -> new RuntimeException("Item não encontrado para o usuário"));

        if (novaQuantidade < 0) {
            throw new IllegalArgumentException("A quantidade não pode ser negativa");
        }

        if (novaQuantidade == 0) {
            userItemRepository.delete(userItem);
            return;
        }

        userItem.setQuantidade(novaQuantidade);
        userItemRepository.save(userItem);
    }
}
