package br.com.warframe.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.warframe.api.entity.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAutorUserId(Long userId);
}
