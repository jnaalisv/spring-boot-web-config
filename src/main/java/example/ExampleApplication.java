package example;

import example.config.ExampleWebMvcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ExampleApplication {

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.setPort(9000);
        factory.setContextPath("/example");
        return factory;
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {

        AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
        servletAppContext.register(ExampleWebMvcConfiguration.class);

        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new DispatcherServlet(servletAppContext));
        servletRegistrationBean.setName("example");
        servletRegistrationBean.setLoadOnStartup(1);
        servletRegistrationBean.setAsyncSupported(true);
        servletRegistrationBean.addUrlMappings("/*");
        return servletRegistrationBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }
}
