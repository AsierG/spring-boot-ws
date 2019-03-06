package com.asierg.wsserver.config;

import com.asierg.wsserver.ws.HelloWorldEndpoint;
import com.asierg.wsserver.ws.interceptor.CustomEndpointInterceptor;
import com.asierg.wsserver.ws.interceptor.GlobalEndpointInterceptor;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadRootSmartSoapEndpointInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;

import javax.servlet.Servlet;
import java.util.List;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

  @Bean
  public ServletRegistrationBean<Servlet> messageDispatcherServlet(
      ApplicationContext applicationContext) {
    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
    servlet.setApplicationContext(applicationContext);

    return new ServletRegistrationBean<>(servlet, "/asierg/ws/*");
  }

  @Bean(name = "helloworld")
  public Wsdl11Definition defaultWsdl11Definition() {
    SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
    wsdl11Definition.setWsdl(new ClassPathResource("/wsdl/helloworld.wsdl"));
    return wsdl11Definition;
  }

  @Override
  public void addInterceptors(List<EndpointInterceptor> interceptors) {
    interceptors.add(new GlobalEndpointInterceptor());

    interceptors.add(
        new PayloadRootSmartSoapEndpointInterceptor(
            new CustomEndpointInterceptor(),
            HelloWorldEndpoint.NAMESPACE,
            HelloWorldEndpoint.HELLOWORLD_REQUEST_LOCAL_PART));
  }
}
