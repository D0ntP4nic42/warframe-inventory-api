package br.com.warframe.api.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(nullable = false, unique = true, length = 50)
	private String username;

	@Column(nullable = false, length = 100)
	private String senha;

	@Column(nullable = false, unique = true, length = 100)
	private String email;

	@Column(nullable = true, unique = true, length = 50)
	private String codConta;

	public User() {
	}

	public User(String username, String email, String senha) {
		this.username = username;
		this.email = email;
		this.senha = senha;
	}

	// Getters e setters
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCodConta() {
		return codConta;
	}

	public void setCodConta(String codConta) {
		this.codConta = codConta;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
