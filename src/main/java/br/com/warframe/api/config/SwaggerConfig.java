package br.com.warframe.api.config;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import io.swagger.v3.oas.models.media.Schema;

@Configuration
public class SwaggerConfig {

	@Value("${user.inicial.email}")
	private String defaultLogin;

	@Value("${user.inicial.senha}")
	private String defaultPassword;

	@Autowired
	Environment env;

	@Bean
	public OpenApiCustomizer customiseLoginExample() {
		return openApi -> {
			var schema = openApi.getComponents().getSchemas().get("LoginDTO");
			if (schema != null && schema.getProperties() != null) {
				Schema<?> emailSchema = (Schema<?>) schema.getProperties().get("email");
				Schema<?> senhaSchema = (Schema<?>) schema.getProperties().get("senha");

				if (emailSchema != null) {
					emailSchema.setExample(defaultLogin);
				}
				if (senhaSchema != null) {
					senhaSchema.setExample(defaultPassword);
				}
			}
		};
	}
}