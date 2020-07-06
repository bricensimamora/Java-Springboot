package apap.tugas.SIRUANG.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/api/v1/**").permitAll()
                // .anyRequest().authenticated()
//                .antMatchers("/peminjaman-ruangan/tambah/**").hasAnyAuthority("GURU")
//                .antMatchers("/peminjaman-ruangan/tambah/**").hasAnyAuthority("SISWA")
//                .antMatchers("/daftar-peminjaman-ruangan/**").permitAll()
//                .antMatchers("/peminjaman-ruangan/ubah-persetujuan-terima/**").hasAnyAuthority("ADMIN")
//                .antMatchers("/peminjaman-ruangan/ubah-persetujuan-tolak/**").hasAnyAuthority("ADMIN")
//                .antMatchers("/fasilitas/**").hasAnyAuthority("ADMIN TU")
                .antMatchers("/user/details/**").permitAll()
                .antMatchers("/ruangan/detail/**").permitAll()
                .antMatchers("/peminjaman-ruangan/**").permitAll()
                .antMatchers("/ruangan/**").permitAll()
                .antMatchers("/user/**").hasAnyAuthority("Admin TU")
//                .antMatchers("/pengadaan-fasilitas/**").hasAnyAuthority("GURU","ADMIN TU")
//                .antMatchers("/pengadaan-buku").hasAnyAuthority("ADMIN TU")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll();
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

//     @Autowired
//     public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception{
//         auth.inMemoryAuthentication()
//                 .passwordEncoder(encoder())
//                 .withUser("nadiem").password(encoder().encode("makarim"))
//                 .roles("ADMIN TU");
//     }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }
}
