package org.spyc.bartabs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(new AuthenticationProvider() {
			
			@Override
			public boolean supports(Class<?> auth) {
				if (UsernamePasswordAuthenticationToken.class.isAssignableFrom(auth)) {
					return true;
				}
				return false;
			}
			
			@Override
			public Authentication authenticate(Authentication auth) throws AuthenticationException {
				if (auth instanceof UsernamePasswordAuthenticationToken) {
					UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)auth;
					String userName = (String)token.getPrincipal();
					String passWord = (String)token.getCredentials();
					
					if ("admin".equals(userName) && "s1erra".equals(passWord)) {
						List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
						authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
						auth = new UsernamePasswordAuthenticationToken(token.getPrincipal(), token.getCredentials(), authorities);
					}
					else {
						throw new BadCredentialsException("Invalid user name or password.");
					}
				}
				return auth;
			}
			
		});
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests()
          .antMatchers("/admin/login*").permitAll()
          .antMatchers("/api/*").anonymous()
          .antMatchers("/css/*").permitAll()
          .antMatchers("/vendor/**").permitAll()
          .antMatchers("/js/*").permitAll()
          .antMatchers("/fonts/**").permitAll()
          .anyRequest().authenticated()
          .and()
          .formLogin()
          .loginPage("/admin/login")
          .defaultSuccessUrl("/admin/index")
          .failureUrl("/admin/login?error=true")
          .and()
          .logout()
          .logoutUrl("/admin/logout")
          .logoutSuccessUrl("/admin/login")
          .and()
          .csrf().disable();

    }
  

}