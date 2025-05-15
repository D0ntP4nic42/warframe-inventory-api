package br.com.warframe.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.warframe.api.entity.User;
import br.com.warframe.api.repository.UserRepository;
import br.com.warframe.api.vo.RegisterDTO;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public User findUser(String usernameOrEmail) {
		var user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
		return user;
	}

	public void saveUser(RegisterDTO registerDTO) {
		userRepository.findByUsernameOrEmail(registerDTO.username(), registerDTO.email()).ifPresent(user -> {
			throw new RuntimeException("Usuário já existe");
		});

		var user = userRepository.save(new User(registerDTO.username(), registerDTO.email(), registerDTO.senha()));
		if (user == null) {
			throw new RuntimeException();
		}
	}
}
