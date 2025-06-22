package br.com.warframe.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.warframe.api.entity.User;
import br.com.warframe.api.repository.UserRepository;
import br.com.warframe.api.vo.RegisterDTO;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	public User findUser(String usernameOrEmail) {
		var user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
		return user;
	}

	public void saveUser(RegisterDTO registerDTO) {
		userRepository.findByUsernameOrEmail(registerDTO.username(), registerDTO.email()).ifPresent(user -> {
			throw new RuntimeException("Usuário já existe");
		});

		User user = new User();
		user.setUsername(registerDTO.username());
		user.setSenha(PASSWORD_ENCODER.encode(registerDTO.senha()));
		user.setEmail(registerDTO.email());

		userRepository.save(user);
	}
}
