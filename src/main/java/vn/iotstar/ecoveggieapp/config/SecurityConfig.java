package vn.iotstar.ecoveggieapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Tắt CSRF để test API từ Postman
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/user/register", "/api/v1/test", "/api/v1/user/login", "/api/v1/products/all", "/api/v1/products/newest", "/api/v1/products/price/asc", "/api/v1/products/price/desc", "/api/v1/products/price/range", "/api/v1/products/price/range", "/api/v1/products/sold/desc", "/api/v1/products/search").permitAll() // Cho phép truy cập không cần đăng nhập
                .anyRequest().authenticated() // Các API khác vẫn yêu cầu xác thực
            )
            .formLogin().disable() // Không dùng form login mặc định
            .httpBasic().disable(); // Không bắt buộc dùng Basic Auth
        return http.build();
    }
}
