package com.kel3.yfaexpress.configuration;

import com.kel3.yfaexpress.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {


	@Autowired
	private PasswordEncoder passwordEncoder;

	//needed for DaoAuthenticationProvider
	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService)
				.passwordEncoder(passwordEncoder);
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//				.authorizeRequests()
//				.antMatchers("/registration**", "/login**", "/js/**", "/css/**", "/fonts/**", "/images/**", "/assets/**",
//						"/*", "/aboutus*", "/product*", "/academic*", "/comic*", "/family*", "/novel*").permitAll()
//				.anyRequest().authenticated()
////				.antMatchers("/home-company*").hasRole("ADMIN")
//				.and()
//				.formLogin()
//				.loginPage("/login").permitAll()
////				.defaultSuccessUrl("/")
//				.and()
//				.logout()
//				.invalidateHttpSession(true)
//				.clearAuthentication(true)
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//
//				//once user click logout button, user will go to login page with "logout" page
//				.logoutSuccessUrl("/login?logout").permitAll();
//	}

//	@Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    //to integrate spring data JPA and hibernate in spring security, we need to provide this
	//spring security provide UserDetailsService interface and to use this, we need to provide userService
	//so that, UserService need to extends UserDetailService interface
	//and in UserServiceImpl, we need to override loadUserByUsername
//	@Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
//        auth.setUserDetailsService(userService);
//        auth.setPasswordEncoder(passwordEncoder());
//        return auth;
//    }

    //to pass authenticationProvider
//	@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/registration*" , "/js/**" , "/css/**" , "/img/**","/plugins/**").permitAll()
				.antMatchers( "/js/**" , "/css/**" , "/img/**","/plugins/**").permitAll()
				.antMatchers("/layanan*" , "/utama**" , "/cekresi*").permitAll()
				.antMatchers(HttpMethod.POST, "/api/**").authenticated()
				.antMatchers(HttpMethod.GET, "/api/**").authenticated()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login").permitAll()
				.loginProcessingUrl("/perform_login")
				.successHandler(myAuthenticationSuccessHandler)
				.failureUrl("/login?error=true")
//				.defaultSuccessUrl("/")
				.and()
				.logout()
//				.invalidateHttpSession(true)
//				.clearAuthentication(true)
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

				//once user click logout button, user will go to login page with "logout" page
				.logoutUrl("/perform_logout")
				.logoutSuccessUrl("/utama?logout=true")
				.deleteCookies("JSESSIONID");
	}

}
