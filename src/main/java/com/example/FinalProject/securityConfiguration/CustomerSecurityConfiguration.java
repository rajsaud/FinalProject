package com.example.FinalProject.securityConfiguration;

import com.example.FinalProject.service.UserDetailServiceSimple;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class CustomerSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailServiceSimple userDetailServiceSimple() {
        return new UserDetailServiceSimple();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder)throws Exception{
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());


//    //Authentication of an user
//    @Override
//    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder)throws Exception{
//        authenticationManagerBuilder.inMemoryAuthentication()
//                .withUser("zorba_123").password(passwordEncoder().encode("zorbaadmin1")).roles("ADMIN","USER")
//                .and()
//                .withUser("zorba_rj").password(passwordEncoder().encode("zorbauser002")).roles("USER")
//                .and()
//                .withUser("zorba_admin").password(passwordEncoder().encode("zorbaadmin001")).roles("ADMIN");
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
    }
    //Authorization of an user
    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/test/saveData") .hasAuthority("ADMIN")// permitAll() for save the data without authentication
                .antMatchers(HttpMethod.GET , "/test/getDataFromFinalProject").hasAnyAuthority("ADMIN" , "USER")
                .antMatchers(HttpMethod.PUT,"/test/updateDataBaseOnCustId" ).hasAuthority("USER")
                .antMatchers(HttpMethod.POST,"/user/saveUserData").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"/user/updateUserInfoBasedOnUserId").hasAuthority("USER")
                .antMatchers(HttpMethod.PUT,"/user/updateUserInfo").hasAuthority("USER")
                .antMatchers(HttpMethod.PUT,"/test/updateCustInfo").hasAuthority("USER")
                .antMatchers(HttpMethod.GET,"/user/fetchUserData").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

}
