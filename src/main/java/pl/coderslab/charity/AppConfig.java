//package pl.coderslab.charity;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import pl.coderslab.charity.service.UserDetailsServiceImpl;
//
//@Configuration
//@ComponentScan("pl.coderslab.charity")
//@EnableJpaRepositories(basePackages = "pl.coderslab.charity.repository")
//@EnableTransactionManagement
//public class AppConfig implements WebMvcConfigurer {
//
//    @Bean
//    public UserDetailsService getUserDetailsService() {
//        return new UserDetailsServiceImpl();
//    }
//}
