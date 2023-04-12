package Playdata.gihun;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// 두개 어노테이션 지정하면 다 알아서 관리해줌
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().antMatchers("/**").permitAll() // localhost에서 이어지는 모든 경로들을 모든 이들에게 접근 허용 가능하게함
                .and().csrf().ignoringAntMatchers("/h2-console/**") // csrf에서 (경로)만 제외함
                .and().headers().addHeaderWriter(new XFrameOptionsHeaderWriter( // 클릭재킹 공격을 막기 위해 Headerwriter부분을 sameorigin으로 처리해 프레임 부분 출력되게 함
                        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN
                )).and().formLogin().loginPage("/user/login").defaultSuccessUrl("/")
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/").invalidateHttpSession(true);
            return http.build();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
        throws Exception{
            return authenticationConfiguration.getAuthenticationManager();
        }
}
