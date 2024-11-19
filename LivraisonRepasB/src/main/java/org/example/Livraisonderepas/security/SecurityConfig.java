package org.example.Livraisonderepas.security;


import org.example.Livraisonderepas.security.jwt.JwtAuthenticationFilter;
import org.example.Livraisonderepas.security.jwt.JwtAutorisationFilter;
import org.example.Livraisonderepas.security.model.User;
import org.example.Livraisonderepas.security.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true)
        //securedEnabled = true,
        //jsr250Enabled = true
public class
SecurityConfig {

    @Lazy
    private JwtAutorisationFilter jwtAutorisationFilter;

    @Autowired
    public void setJwtAutorisationFilter( JwtAutorisationFilter jwtAutorisationFilter) {
        this.jwtAutorisationFilter = jwtAutorisationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserDao userDao;
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    UserDetailsService userDetailsService(){
        return (username -> {
            User user = userDao.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("user " + username + " not founded");
            } else {
                return user;
            }
        });
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager)  throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(customizer -> customizer.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.POST, "/api/user/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/user/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/admin/").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/admin/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/admin/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/admin/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/admin/**").hasAuthority("ADMIN")



                        .requestMatchers(HttpMethod.POST, "/api/client/**").hasAuthority("CLIENT")
                        .requestMatchers(HttpMethod.GET, "/api/client/**").hasAuthority("CLIENT")
                        .requestMatchers(HttpMethod.DELETE, "/api/client/**").hasAuthority("CLIENT")
                        .requestMatchers(HttpMethod.PUT, "/api/client/**").hasAuthority("CLIENT")

                        .requestMatchers(HttpMethod.GET, "/api/livreur/**").hasAuthority("LIVREUR")
                        .requestMatchers(HttpMethod.PUT, "/api/livreur/**").hasAuthority("LIVREUR")


                        .anyRequest().authenticated()
                )
                .addFilter(new JwtAuthenticationFilter(authenticationManager))
                .addFilterBefore(jwtAutorisationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



}
