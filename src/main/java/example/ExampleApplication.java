package example;

import example.config.ExampleWebMvcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ExampleApplication {

    @Bean
    public EmbeddedServletContainerFactory servletContainer(Environment environment) {
        JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
        factory.setPort(environment.getProperty("server.port", Integer.class));
        factory.setContextPath("");
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
