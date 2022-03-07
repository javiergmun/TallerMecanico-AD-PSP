package com.taller2dam.taller.security;

import com.taller2dam.taller.security.jwt.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
//Nos permitirá más adelante indicar quien puede acceder, si es necesario estas autenticado o no...
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
                   // .antMatchers(HttpMethod.GET, "/mecanicos/**").hasRole("ADMIN")
                   // .antMatchers(HttpMethod.POST, "/mecanicos/**").hasRole("ADMIN")
                   // .antMatchers(HttpMethod.PUT, "/mecanicos/**").hasRole("ADMIN")
                   // .antMatchers(HttpMethod.DELETE, "/mecanicos/**").hasRole("ADMIN")
//
                   // .antMatchers(HttpMethod.GET, "/servicios/**").hasAnyRole("USER", "ADMIN")
                   // .antMatchers(HttpMethod.POST, "/servicios/**").hasRole("ADMIN")
                   // .antMatchers(HttpMethod.PUT, "/servicios/**").hasRole("ADMIN")
                   // .antMatchers(HttpMethod.DELETE, "/servicios/**").hasRole("ADMIN")
//
                // .antMatchers(HttpMethod.GET, "/usuarios/**").hasAnyRole("USER", "ADMIN")
                // .antMatchers(HttpMethod.POST, "/usuarios/**").hasRole("ADMIN")
                // .antMatchers(HttpMethod.PUT, "/usuarios/**").hasRole("ADMIN")
                // .antMatchers(HttpMethod.DELETE, "/usuarios/**").hasRole("ADMIN")


                //sobre cualquier otra petición no reflejada solo pediremos que esté autenticado
                    .anyRequest().not().authenticated();

        //Añadimos el filtro
        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
