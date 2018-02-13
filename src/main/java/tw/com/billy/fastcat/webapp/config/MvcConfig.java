package tw.com.billy.fastcat.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 
 * @author Billy
 * replace  dispatcher-servlet.xml
 */
@Configuration
// Marks this class as configuration
// Specifies which package to scan
@ComponentScan(basePackages="tw.com.billy.fastcat.webapp.controller")
// Enables Spring's annotations
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter{  
    
    /**
     * The caveat of mapping DispatcherServlet to “/” is that by default 
     * it breaks the ability to serve static resources like images and CSS files.
     * To remedy this, I need to configure Spring MVC to enable defaultServletHandling.
     * 
     * http://zeroturnaround.com/rebellabs/your-next-java-web-app-less-xml-no-long-restarts-fewer-hassles-part-1/ 
     */
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations(
                "/resources/");
    }
    
    @Bean
    public ViewResolver setupViewResolver() {
        InternalResourceViewResolver  resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        //resolver.setSuffix(".jsp");
        resolver.setSuffix(".jsp");
        
        return resolver;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new tw.com.billy.fastcat.webapp.interceptor.BasicInterceptor());
    }
    
}
