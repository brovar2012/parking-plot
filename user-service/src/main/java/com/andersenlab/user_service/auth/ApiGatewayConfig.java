//package com.andersenlab.user_service.auth;
//
//@Configuration
//@EnableWebSecurity
//public class ApiGatewayConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/public/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .oauth2ResourceServer()
//                .jwt(); // Или любой другой метод валидации токена
//    }
//}
