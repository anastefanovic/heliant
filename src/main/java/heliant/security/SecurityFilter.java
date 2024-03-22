package heliant.security;

import heliant.enumeration.Rola;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityFilter {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(sessionMangConfig -> sessionMangConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authConfig -> {
                    authConfig.requestMatchers("/auth/**").permitAll();
                    authConfig.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll();
                    authConfig.requestMatchers("/error").permitAll();

                    authConfig.requestMatchers( HttpMethod.GET,"/formular/read/**")
                            .hasAnyAuthority(Rola.ADMIN.name(), Rola.RADNIK.name());
                    authConfig.requestMatchers( HttpMethod.POST, "/formular/create")
                            .hasAuthority(Rola.ADMIN.name());
                    authConfig.requestMatchers(HttpMethod.PUT, "/formular/update")
                            .hasAuthority(Rola.ADMIN.name());
                    authConfig.requestMatchers(HttpMethod.DELETE, "/formular/delete/**")
                            .hasAuthority(Rola.ADMIN.name());

                    authConfig.requestMatchers( HttpMethod.GET,"/polje/read/**")
                            .hasAnyAuthority(Rola.ADMIN.name(), Rola.RADNIK.name());
                    authConfig.requestMatchers( HttpMethod.POST, "/polje/create")
                            .hasAuthority(Rola.ADMIN.name());
                    authConfig.requestMatchers(HttpMethod.PUT, "/polje/update")
                            .hasAuthority(Rola.ADMIN.name());
                    authConfig.requestMatchers(HttpMethod.DELETE, "/polje/delete/**")
                            .hasAuthority(Rola.ADMIN.name());

                    authConfig.requestMatchers("/formular_popunjen/**")
                            .hasAnyAuthority(Rola.ADMIN.name(), Rola.RADNIK.name());

                    authConfig.requestMatchers("/polje_popunjeno/**")
                            .hasAnyAuthority(Rola.ADMIN.name(), Rola.RADNIK.name());

                    authConfig.anyRequest().denyAll();
                });

        return httpSecurity.build();
    }
}
