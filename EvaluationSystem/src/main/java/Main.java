import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import security.CorsFilter;
import security.JwtFilter;

@SpringBootApplication
@ComponentScan(basePackages = {"controller", "dao", "service", "security"})
public class Main {

    private JwtFilter jwtFilter;
    private CorsFilter corsFilter;

    public Main(JwtFilter jwtFilter, CorsFilter corsFilter) {
        this.jwtFilter = jwtFilter;
        this.corsFilter = corsFilter;
    }

    @Bean
    public FilterRegistrationBean jwtFilterBean() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(jwtFilter);
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setOrder(2);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean corsFilterBean() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(corsFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);

        return registrationBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}