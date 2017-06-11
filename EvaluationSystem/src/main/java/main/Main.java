package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import security.JwtFilter;

@SpringBootApplication
@ComponentScan(basePackages = {"controller", "dao", "service", "security"})
public class Main {

    JwtFilter jwtFilter;

    public Main(JwtFilter jwtFilter){
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(jwtFilter);
        registrationBean.addUrlPatterns("/api/*");

        return registrationBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}