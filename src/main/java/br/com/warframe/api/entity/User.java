package br.com.warframe.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	@Column(name = "senha", nullable = false)
	private String senha;
	
	public User() {
	}

	public User(String username, String email, String senha) {
		this.username = username;
		this.email = email;
		this.senha = senha;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

}
