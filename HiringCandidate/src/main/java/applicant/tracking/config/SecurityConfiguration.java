package applicant.tracking.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	
		 @Override
		    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		        // Set your configuration on the auth object
		        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder());
		    }
		 
		   @Override
		    protected void configure(HttpSecurity http) throws Exception {
		        http.csrf().disable().
		        authorizeRequests()
		                .antMatchers("/candidates").hasAnyRole("ADMIN","USER")
		                .antMatchers("/roles").hasAnyRole("ADMIN","USER")
		                .antMatchers("/users").hasAnyRole("ADMIN","USER")
		                .antMatchers("/candidates/{id}").hasRole("ADMIN")
		                .antMatchers("/roles/{id}").hasRole("ADMIN")
		                .antMatchers("/users/{id}").hasRole("ADMIN")
		                .antMatchers("/").permitAll()
		                .and().formLogin();
	    }
}
