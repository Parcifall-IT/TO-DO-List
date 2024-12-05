package ru.Parcifall.NauJava;

import net.bull.javamelody.MonitoringFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ru.Parcifall.NauJava.handler.CustomAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class Config {
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/registration", "/login").permitAll()
                .requestMatchers("/home", "/add-task", "/edit-profile", "/update-task",
                        "/edit-task", "/toggle-task-completion/**").authenticated()
                .anyRequest().hasRole("ADMIN"))
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(customSuccessHandler())
                        .permitAll())
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/toggle-task-completion/**"))
                .exceptionHandling(ex -> ex.accessDeniedPage("/home"));
        return httpSecurity.build();
    }

    @Bean
    public FilterRegistrationBean<MonitoringFilter> monitoringFilter() {
        FilterRegistrationBean<MonitoringFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MonitoringFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
