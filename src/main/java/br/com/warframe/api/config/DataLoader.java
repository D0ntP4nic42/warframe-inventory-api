package br.com.warframe.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.warframe.api.entity.User;
import br.com.warframe.api.repository.UserRepository;

@Component
public class DataLoader implements ApplicationRunner {
	private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
	
	@Autowired
	private UserRepository userRepository;

	@Value("${user.inicial.email}")
	private String EMAIL_INICIAL;

	@Value("${user.inicial.name}")
	private String USERNAME_INICIAL;
	
	@Value("${user.inicial.senha}")
	private String SENHA_INICIAL;

	@Override
	public void run(ApplicationArguments args) {
		var user = userRepository.findByUsernameOrEmail(EMAIL_INICIAL, USERNAME_INICIAL);
		if (user.isPresent()) {
			System.out.println("\n\n ===== Usuário inicial já existe ===== \n\n");
			return;
		}
		
		userRepository.save(new User(EMAIL_INICIAL, USERNAME_INICIAL, PASSWORD_ENCODER.encode(SENHA_INICIAL)));
		System.out.println("\n\n ===== Usuário inicial cadastrado com sucesso ===== \n\n");
	}
}