package com.beta.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.beta.constant.SecurityConstant;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).httpBasic(Customizer.withDefaults())
				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeRequests(requests -> requests.antMatchers(SecurityConstant.SWAGGER_WHITELIST_URL).permitAll()
						.antMatchers("/**").authenticated());
	}

//	@Bean
//	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
////		http.authorizeRequests().antMatchers(SecurityConstant.SWAGGER_WHITELIST_URL).permitAll().anyRequest()
////				.authenticated();
////		http.authorizeRequests().anyRequest().authenticated();
//		http.authorizeRequests(auth -> auth.anyRequest().authenticated());
//		http.httpBasic(Customizer.withDefaults());
//		http.csrf().disable();
//		return http.build();
//	}
}
