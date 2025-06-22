package br.com.warframe.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.warframe.api.entity.Post;
import br.com.warframe.api.entity.User;
import br.com.warframe.api.repository.PostRepository;
import br.com.warframe.api.repository.UserRepository;
import br.com.warframe.api.vo.CreatePostDTO;
import br.com.warframe.api.vo.PostResponseDTO;
import jakarta.transaction.Transactional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public PostResponseDTO criarPost(CreatePostDTO dto, String usernameOrEmail) {
        User user = userRepository.findByUsernameOrEmail(
                usernameOrEmail,
                usernameOrEmail)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Post post = new Post();
        post.setAutor(user);
        post.setConteudo(dto.conteudo());
        post.setDataCriacao(LocalDateTime.now());

        Post salvo = postRepository.save(post);
        return toDTO(salvo);
    }

    public List<PostResponseDTO> listarTodos() {
        return postRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<PostResponseDTO> listarPorUsuario(Long userId) {
        return postRepository.findByAutorUserId(userId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private PostResponseDTO toDTO(Post post) {
        PostResponseDTO dto = new PostResponseDTO();
        dto.setId(post.getPostId());
        dto.setConteudo(post.getConteudo());
        dto.setAutorUsername(post.getAutor().getUsername());
        dto.setDataCriacao(post.getDataCriacao());
        return dto;
    }
}