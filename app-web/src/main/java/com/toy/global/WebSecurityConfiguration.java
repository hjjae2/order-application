package com.toy.global;

import com.toy.apps.member.entity.Member;
import com.toy.global.security.ToyAccessDeniedHandler;
import com.toy.global.security.ToyAuthenticationEntryPoint;
import com.toy.apps.member.service.ToyMemberDetailService;
import com.toy.global.security.ToyAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.preauth.x509.X509AuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final ToyMemberDetailService toyMemberDetailService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
         * 기타(session, filter, deniedHandler, ...) 설정입니다.
         * */
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(new ToyAuthenticationFilter(toyMemberDetailService), X509AuthenticationFilter.class);
        http.exceptionHandling().accessDeniedHandler(new ToyAccessDeniedHandler());
        http.exceptionHandling().authenticationEntryPoint(new ToyAuthenticationEntryPoint());

        http.csrf().ignoringAntMatchers("**");
        http.headers().frameOptions().disable();

        /*
         * authorizeRequests 설정입니다.
         * */
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/orders/**").authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/members", "/login", "/h2/**").anonymous();
        http.authorizeRequests().antMatchers(HttpMethod.POST).authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.PUT).authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.DELETE).hasRole(Member.Type.ADMIN.name());
        http.authorizeRequests().anyRequest().permitAll();
    }
}
