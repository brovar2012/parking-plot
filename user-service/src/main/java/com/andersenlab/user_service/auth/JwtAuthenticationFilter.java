//package com.andersenlab.user_service.auth;
//
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
//        // Реализуйте логику аутентификации, например, извлечение токена из заголовка и проверку его
//        return null;
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        // Логика обработки успешной аутентификации
//        super.successfulAuthentication(request, response, chain, authResult);
//    }
//
//
//    //////
//
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
//        // Логика для проверки токена JWT
//        String token = request.getHeader("Authorization");
//        if (token != null && token.startsWith("Bearer ")) {
//            token = token.substring(7);
//            // Проверка и декодирование токена
//            if (isValidToken(token)) {
//                // Установка аутентификации в контекст
//                return new UsernamePasswordAuthenticationToken("user", null, null);
//            }
//        }
//        return null;
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        // Логика успешной аутентификации
//        super.successfulAuthentication(request, response, chain, authResult);
//    }
//
//    private boolean isValidToken(String token) {
//        // Реальная логика проверки токена
//        return true; // Замените на вашу логику проверки токенов
//    }
//
//    //////
//
//    @Bean
//    public JwtAuthenticationConverter jwtAuthenticationConverter() {
//        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
//
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
//        return jwtAuthenticationConverter;
//    }
//}
