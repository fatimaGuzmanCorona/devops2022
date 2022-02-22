package mx.edu.itlapiedad;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import mx.edu.itlapiedad.JWTAuthorizationFilter;


@EnableWebSecurity
	@Configuration
	public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
	
	protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/api/mensajes/login").permitAll()
				.anyRequest().authenticated();
		}

} // FIN class
	
	// bloquear los planes, cualquier controlador debe de estar bloqueado, solo el uri de login debe de estar disponible
	// token