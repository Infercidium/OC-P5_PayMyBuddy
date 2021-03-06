package fr.infercidium.PayMyBuddy.configuration;

import fr.infercidium.PayMyBuddy.service.UserI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    /**
     * Instantiation of userInterface.
     */
    @Autowired
    private UserI userService;

    /**
     * Password encoding bean.
     * @return the password encoder.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Authentication Bean.
     * @return program authentication.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    /**
     * Authentication configuration.
     * @param auth : program authentication.
     */
    @Override
    public void configure(final AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    /**
     * Configuration of access to the program's html.
     * @param http used in configuration.
     * @throws Exception global.
     */
    @Override
    public void configure(final HttpSecurity http) throws Exception {
       http
               .authorizeRequests()
               .antMatchers("/custom.css").permitAll()
               .antMatchers("/registration**").permitAll()
               .antMatchers("/login**").permitAll()
               .antMatchers("/billing**").hasAuthority("Admin")
               .anyRequest().authenticated()
               .and().formLogin()
                    .loginPage("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/home")
                    .permitAll()
               .and().logout()
                    .invalidateHttpSession(true).clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout")
                    .permitAll();
    }
}
