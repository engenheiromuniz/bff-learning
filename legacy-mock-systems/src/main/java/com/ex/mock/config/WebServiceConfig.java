package com.ex.mock.config;

import com.ex.mock.soap.endpoint.AntecedentesEndpoint;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {

    @Bean
    public AntecedentesEndpoint antecedentesEndpoint() {
        return new AntecedentesEndpoint();
    }

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
            WebApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    // WSDL fica disponivel em: http://localhost:8081/ws/antecedentes.wsdl
    @Bean(name = "antecedentes")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema antecedentesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("AntecedentesPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://ex.com/mock/antecedentes");
        wsdl11Definition.setSchema(antecedentesSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema antecedentesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/antecedentes.xsd"));
    }
}
