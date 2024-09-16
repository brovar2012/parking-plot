package com.andersenlab.user_service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {


//    private final AuthEntryPointJwt unauthorizedHandler;
//
//    private final AuthTokenFilter authenticationJwtTokenFilter;

    private final AuthenticationManager authenticationManager;

//    private final PasswordEncoder passwordEncoder;

    private final AuthenticationProvider authenticationProvider;
    private final JwtRequestFilter jwtRequestFilter;

    private static final String[] AUTH_WHITELIST = {
            "/v1/**"
    };

    RequestMatcher publicEndpoints = new OrRequestMatcher(
            new AntPathRequestMatcher("/v1/auth/login"),
            new AntPathRequestMatcher("/v1/auth/signup"),
            new AntPathRequestMatcher("/v1/auth/say")
    );

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers(publicEndpoints)
                        .permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/auth/signup").hasRole("SUPER_ADMIN")
                        .anyRequest().authenticated())
//                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
//                .and()
//                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
//                .and()
//                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                .headers().contentSecurityPolicy("script-src 'self' " + mizaLinkProperties.getLink() + "; style-src 'self' " +
//                        mizaLinkProperties.getLink() + "; img-src 'self' " + mizaLinkProperties.getLink() + ";");
        return http.build();
    }

//    private static final String[] AUTH_WHITELIST = {
//            "/swagger-resources",
//            "/swagger-resources/**",
//            "/configuration/ui",
//            "/configuration/security",
//            "/swagger-ui.html",
//            "/webjars/**",
//            "/v3/api-docs/**",
//            "/api/v1/**",
//            "/api/public/**",
//            "/api/public/authenticate",
//            "/actuator/**",
//            "/swagger-ui/**",
//            "/api/auth/login",
//            "/api/auth/otp/login",
//            "/api/auth/logout",
//            "/api/auth/refresh-token",
//            "/api-docs/**",
//            "/api/users/**",
//            "/api/roles/**",
//            "/api/invitations/**",
//            "/api/providers/**",
//            "/api/prices/**",
//            "/api/services/**",
//            "/api/serviceFilters/**",
//            "/api/investor/auth/**",
//            "/api/communicationRequests/**",
//            "/api/v1/notifications/**"
//    };


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.csrf().disable()
////                .cors().disable()
////                .authorizeHttpRequests().antMatchers("/v1/auth/login", "/v1/auth/signup").permitAll()
//                .authorizeHttpRequests().antMatchers(AUTH_WHITELIST).permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .authenticationManager(authenticationManager)
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        return http.build();
//
//    }



//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .authorizeRequests()
//                .antMatchers(UrlMapping.AUTH + UrlMapping.SIGN_UP).permitAll()
//                .antMatchers(UrlMapping.AUTH + UrlMapping.LOGIN).permitAll()
////                .antMatchers(UrlMapping.VALIDATE_JWT).permitAll()
//                .antMatchers("/api/test/**").permitAll()
//                .anyRequest().authenticated();
//
//        http.addFilterBefore(authenticationJwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();

//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.userDetailsService(userDetailsService);
//        authenticationManagerBuilder
//        authenticationManager = authenticationManagerBuilder.build();

//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/login", "/register").permitAll()  // Разрешаем доступ к этим эндпоинтам без аутентификации
//                .anyRequest().authenticated();  // Остальные запросы требуют аутентификации


//        http.csrf().disable()
////                .cors().disable()
//                .authorizeHttpRequests().antMatchers("/v1/auth/login", "/v1/auth/signup").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .authenticationManager(authenticationManager)
//                .authenticationProvider(authenticationProvider)
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        return http.build();
//
//    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedMethods("*");
//            }
//        };
//    }

//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .oauth2ResourceServer()
//                .jwt();
//    }
//
//    @Bean
//    public JwtDecoder jwtDecoder() {
//        // Замените URL на адрес вашего сервиса с публичными ключами или используйте ключ вручную
//        return NimbusJwtDecoder.withJwkSetUri("https://example.com/.well-known/jwks.json").build();
//    }
//
//    ////
//
//
//    @Bean
//    public JwtDecoder jwtDecoder() {
//        // Замените публичный ключ на ваш реальный публичный ключ
//        RSAPublicKey publicKey = ... ; // Получите публичный ключ из конфигурации или файла
//        return NimbusJwtDecoder.withPublicKey(publicKey).build();
//    }
}
