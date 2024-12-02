package hello.lol.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        HttpSecurity logout1 = http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login.do", "/signup", "/findpassword", "/css/**", "/js/**", "/images/**").permitAll() // 인증 없이 접근 가능
                        .anyRequest().authenticated() // 나머지 요청은 인증 필요
                )
                .formLogin(form -> form
                        .loginPage("/login.do") // 커스텀 로그인 페이지 경로
                        .loginProcessingUrl("/login") // 폼의 action 경로
                        .defaultSuccessUrl("/home", true) // 로그인 성공 시 이동할 경로
                        .failureUrl("/login.do?error=true") // 로그인 실패 시 이동할 경로
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // 로그아웃 요청 경로
                        .logoutSuccessUrl("/login.do") // 로그아웃 성공 시 이동할 경로
                        .permitAll()
                );

        return http.build();
    }
}
