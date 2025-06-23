package br.com.warframe.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.warframe.api.service.UserItemService;
import br.com.warframe.api.vo.AddUserItemDTO;
import br.com.warframe.api.vo.AtualizarQuantidadeDTO;
import br.com.warframe.api.vo.UserItemResponseDTO;

@RestController
@RequestMapping("/user-items")
@Tag(name = "Inventário", description = "Rotas para adicionar itens ao usuário")
@SecurityRequirement(name = "bearerAuth")
public class UserItemController {

    private final UserItemService userItemService;

    public UserItemController(UserItemService userItemService) {
        this.userItemService = userItemService;
    }

    @Operation(summary = "Adicionar item ao usuário", description = "Adiciona uma quantidade específica de um item ao usuário")
    @ApiResponse(responseCode = "200", description = "Item adicionado ou atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário ou item não encontrado", content = @Content)
    @PostMapping
    public ResponseEntity<Void> adicionarItem(@RequestBody AddUserItemDTO dto, Principal principal) {
        userItemService.adicionarItemAoUsuario(principal.getName(), dto.itemDTO(), dto.quantidade());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Listar itens do usuário", description = "Retorna todos os itens vinculados a um usuário com suas quantidades")
    @ApiResponse(responseCode = "200", description = "Itens retornados com sucesso", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    @GetMapping("/inventario")
    public ResponseEntity<List<UserItemResponseDTO>> listarItens(Principal principal) {
        return ResponseEntity.ok(userItemService.listarItensDoUsuario(principal.getName()));
    }

    @Operation(summary = "Atualizar quantidade de um item do usuário", description = "Atualiza a quantidade de um item já presente no inventário do usuário")
    @ApiResponse(responseCode = "200", description = "Quantidade atualizada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    @ApiResponse(responseCode = "404", description = "Usuário ou item não encontrado", content = @Content)
    @PostMapping("/atualizar-quantidade")
    public ResponseEntity<Void> atualizarQuantidade(@RequestBody AtualizarQuantidadeDTO dto,
            Principal principal) {
        userItemService.atualizarQuantidade(principal.getName(), dto.getItemId(), dto.getQuantidade());
        return ResponseEntity.ok().build();
    }
}
