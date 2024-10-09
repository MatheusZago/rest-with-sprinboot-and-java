package br.com.matheus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer){
        //Vamos usar query param para definir o tipo de dado
        //http://localhost:8080/api/person/v1?mediaType=xml

        /* configurer.favorParameter(true)  //Aceita parametro
                .parameterName("mediaType") //Nome do parametro
                .ignoreAcceptHeader(true)  //Ignorar parametros do heador
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON) //Ta falando quais aceita
                .mediaType("xml", MediaType.APPLICATION_XML); */

        //Via header param:
        configurer.favorParameter(false)  //não aceita parametro de query
                .ignoreAcceptHeader(false)  //aceitar header
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON) //Ta falando quais aceita
                .mediaType("xml", MediaType.APPLICATION_XML);
    }
}
