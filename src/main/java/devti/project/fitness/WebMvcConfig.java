package devti.project.fitness;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer  {
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") 
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
	
//	@Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//		
//	    registry.addViewController("/login").setViewName("forward:/index.html");
//	    registry.addViewController("/logout").setViewName("forward:/index.html");
//
//    }
//	
//	

	
	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/fitness/**")
	                .addResourceLocations("classpath:/static/");
	    }
	 
}
