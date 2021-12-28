package com.example.demo.config;

import java.io.IOException;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.commons.CommonsXsdSchemaCollection;


@Configuration
@EnableWs
public class WSConfig extends WsConfigurerAdapter{
	
	public final static String  NAMESPACE = "http://www.baeldung.com/springsoap";
	
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/soapws/*");
	}
	
	@Bean(name = "clientes")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema clientesSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("ClientesPort");
		wsdl11Definition.setLocationUri("/soapws");
		wsdl11Definition.setTargetNamespace(NAMESPACE);
		wsdl11Definition.setSchema(clientesSchema);
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema clientesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("cliente.xsd"));
	}
	

}