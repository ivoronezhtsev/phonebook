package ru.voronezhtsev.phonebook.security


import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.web.context.WebApplicationContext
import javax.sql.DataSource

@Configuration
@EnableWebSecurity
@ComponentScan("ru.voronezhtsev.phonebook.security")
class SecurityConfig {
    @Autowired
    private val applicationContext: WebApplicationContext? = null

    //@Autowired todo Разобраться
    //private AuthenticationSuccessHandlerImpl successHandler;
    @Autowired
    private val dataSource: DataSource? = null

    private var userDetailsService: AppUserDetailsService? = null

    @PostConstruct
    fun completeSetup() {
        userDetailsService = applicationContext!!.getBean(AppUserDetailsService::class.java)
    }

    @Bean
    @Throws(Exception::class)
    fun users(http: HttpSecurity): UserDetailsManager {
        val authenticationManagerBuilder: AuthenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(encoder())
        authenticationManagerBuilder.authenticationProvider(authenticationProvider())
        val authenticationManager: AuthenticationManager = authenticationManagerBuilder.build()

        val jdbcUserDetailsManager = JdbcUserDetailsManager(dataSource)
        jdbcUserDetailsManager.setAuthenticationManager(authenticationManager)
        return jdbcUserDetailsManager
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web -> web.ignoring().requestMatchers("/resources/**") }
    }

    /*@Bean todo Разобраться
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry.requestMatchers("/login").permitAll())
                .formLogin(httpSecurityFormLoginConfigurer ->
                        httpSecurityFormLoginConfigurer.permitAll().successHandler(successHandler))
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }*/
    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsService)
        authProvider.setPasswordEncoder(encoder())
        return authProvider
    }

    @Bean
    fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder(11)
    } /* @Bean todo Разобраться
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }*/
}