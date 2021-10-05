package fr.infercidium.PayMyBuddy.configuration;

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
public class Security extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
       http
               .authorizeRequests()
               .antMatchers("/custom.css").permitAll()
               .antMatchers("/registration").permitAll()
               .anyRequest().authenticated()
               .and().formLogin()
                    .permitAll()
                    .loginPage("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/home")
               .and().logout()
                   .permitAll()
                   .logoutSuccessUrl("/login?logout");
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
      auth.inMemoryAuthentication()
              .withUser("springuser@test.fr").password(passwordEncoder().encode("spring123")).roles("USER")
         .and()
         .withUser("springadmin@test.fr").password(passwordEncoder().encode("admin123")).roles("ADMIN", "USER");
    }
}

