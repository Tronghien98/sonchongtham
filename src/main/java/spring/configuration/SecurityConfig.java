package spring.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.passwordEncoder(passwordEncoder())
				.usersByUsernameQuery("SELECT username,password,enabled FROM users WHERE username = ?")
				.authoritiesByUsernameQuery("SELECT username,name FROM users u INNER JOIN roles r ON u.roleId = r.id WHERE username = ?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/").permitAll()
				.and()
				.formLogin()
				.loginPage("/auth/dang-nhap")
				.loginProcessingUrl("/auth/dang-nhap")
				.usernameParameter("username")
				.passwordParameter("password")
				.failureUrl("/auth/dang-nhap?msg=Error")
				.defaultSuccessUrl("/admin/trang-chu.html", false)
				.and()
				.logout()
				.logoutUrl("/auth/logout")
				.logoutSuccessUrl("/auth/dang-nhap")
				.and()
				.exceptionHandling()
				.accessDeniedPage("/error/403")
				.and()
				.csrf().disable();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
