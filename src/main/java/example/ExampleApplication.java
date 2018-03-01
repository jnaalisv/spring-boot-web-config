package example;

import example.config.ExampleWebMvcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ExampleApplication {

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory(Environment environment) {
        JettyServletWebServerFactory factory = new JettyServletWebServerFactory();
        factory.setPort(environment.getProperty("server.port", Integer.class));
        return factory;
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {

        AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
        servletAppContext.register(ExampleWebMvcConfiguration.class);

        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean<>(new DispatcherServlet(servletAppContext));
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
