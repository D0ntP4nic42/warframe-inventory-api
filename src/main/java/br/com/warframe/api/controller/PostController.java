package br.com.warframe.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.warframe.api.service.PostService;
import br.com.warframe.api.vo.CreatePostDTO;
import br.com.warframe.api.vo.PostResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/posts")
@Tag(name = "Post", description = "Rotas para manipulação de postagens dos usuários")
public class PostController {

    @Autowired
    private PostService postService;

    @Operation(summary = "Criar um novo post", description = "Recebe dados do post e cria um novo post vinculado ao usuário informado.")
    @ApiResponse(responseCode = "200", description = "Post criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    @PostMapping
    public ResponseEntity<PostResponseDTO> criarPost(@RequestBody CreatePostDTO dto) {
        PostResponseDTO postCriado = postService.criarPost(dto);
        return ResponseEntity.ok(postCriado);
    }

    @Operation(summary = "Listar todos os posts", description = "Retorna todos os posts criados no sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de posts retornada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostResponseDTO.class)))
    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> listarTodos() {
        return ResponseEntity.ok(postService.listarTodos());
    }

    @Operation(summary = "Listar posts por usuário", description = "Retorna todos os posts criados por um usuário específico.")
    @ApiResponse(responseCode = "200", description = "Lista de posts do usuário retornada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<PostResponseDTO>> listarPorUsuario(@PathVariable Long userId) {
        return ResponseEntity.ok(postService.listarPorUsuario(userId));
    }
}
