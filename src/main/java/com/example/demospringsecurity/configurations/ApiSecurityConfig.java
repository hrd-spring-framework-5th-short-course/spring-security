package com.example.demospringsecurity.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class ApiSecurityConfig {

    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {

            http.csrf().disable();

            http.
                    sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            http
                    .antMatcher("/api/**")
                    .authorizeRequests()
                    .anyRequest().hasRole("API")
                    .and()
                    .httpBasic();
        }

        @Bean
        public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
            StrictHttpFirewall firewall = new StrictHttpFirewall();
            firewall.setAllowUrlEncodedSlash(true);
            firewall.setAllowSemicolon(true);
            firewall.setAllowBackSlash(true);
            firewall.setAllowUrlEncodedPercent(true);
            firewall.setAllowUrlEncodedPeriod(true);
            return firewall;
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");

            web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
        }





    }


    @Configuration
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        public PasswordEncoder passwordEncoder;

        @Autowired
        private AuthenticationEntryPoint authenticationEntryPoint;

        @Autowired
        private AuthenticationSuccessHandler successHandler;

        @Autowired
        @Qualifier("customUserDetailService")
        private UserDetailsService userDetailsService;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {

       /* auth
                .inMemoryAuthentication()
                .withUser("sophat").password(passwordEncoder.encode("123456")).roles("USER")

                .and()
                .withUser("longheng").password(passwordEncoder.encode("123")).roles("DBA")

                .and()
                .withUser("admin").password("admin").roles("ADMIN", "API");*/

            auth.userDetailsService(userDetailsService);

        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .formLogin()
                    .loginPage("/login-page")
                    .loginProcessingUrl("/login/submit")
                    .usernameParameter("name") // default username
                    .passwordParameter("pass") // default password
                    .successHandler(successHandler)
//                .failureHandler()
                    .permitAll();

            http
                    .authorizeRequests()
                    .antMatchers("/user/**").hasAnyRole("USER", "DBA", "ADMIN")
                    .antMatchers("/dba/**").hasAnyRole("DBA", "ADMIN")
                    .antMatchers("/admin/**").hasRole("ADMIN")

                    .anyRequest().authenticated()
                    .antMatchers("/login").permitAll();

            http
                    .logout()
                    .logoutSuccessUrl("/login")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));


            // enable custom authentication entry point
            http
                    .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint);

            // when session expired redirect to login page
            http.sessionManagement()
//                .invalidSessionUrl("/resources/image.png");
                    .invalidSessionUrl("/login-page");
        }


        @Override
        public void configure(WebSecurity web) throws Exception {
            web
                    .ignoring()
                    .antMatchers("/resources/**", "/images/**");
//                .antMatchers(HttpMethod.GET,"/resources/**");
        }

    }
}
