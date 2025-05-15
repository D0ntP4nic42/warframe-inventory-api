package br.com.warframe.api.controller;

import java.time.Instant;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.warframe.api.entity.User;
import br.com.warframe.api.service.UserService;
import br.com.warframe.api.vo.LoginDTO;
import br.com.warframe.api.vo.RegisterDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Rotas de autenticação")
public class AuthController {
	@Autowired
	private Environment env;

	@Autowired
	private JwtEncoder jwtEncoder;

	@Autowired
	private UserService userService;

	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	@Operation(summary = "Realizar login", description = "Recebe CPF e senha e retorna um token JWT válido caso as credenciais estejam corretas.")
	@ApiResponse(responseCode = "200", description = "Login bem-sucedido, token JWT retornado", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...\"}")))
	@ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content())
	@ApiResponse(responseCode = "401", description = "Credenciais inválidas", content = @Content())
	@ApiResponse(responseCode = "500", description = "Um erro desconhecido ocorreu", content = @Content())
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
		var user = userService.findUser(loginDTO.email());

		if (PASSWORD_ENCODER.matches(loginDTO.senha(), user.getSenha())) {
			return ResponseEntity.ok(Collections.singletonMap("token", gerarAccessToken(user)));
		}

		throw new BadCredentialsException("Credenciais inválidas");
	}

	@Operation(summary = "Registrar usuário", description = "Recebe nome, e-mail e senha e registra um novo usuário.")
	@ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso", content = @Content())
	@ApiResponse(responseCode = "400", description = "Usuário já existe", content = @Content())
	@ApiResponse(responseCode = "500", description = "Um erro desconhecido ocorreu", content = @Content())
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
		userService.saveUser(registerDTO);
		return ResponseEntity.ok("Usuário registrado com sucesso");
	}

	private String gerarAccessToken(User user) {
		var now = Instant.now();
		var expiresIn = 86400;

		var claims = JwtClaimsSet.builder().issuer("iris-api").subject(user.getEmail().toString()).issuedAt(now)
				.claim("role", user).expiresAt(now.plusSeconds(expiresIn)).build();

		var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

		return jwtValue;
	}
}