package com.lbg.open_banking.config;



import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("LLOYD's BANK ATM LOCATION API")
                        .description("A sample atm location api")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("ATM Location Api Documentation")
                        .url("https://developer.lloydsbank.com/opendata-v2.2#head-atms-2.2"));
    }

    @Bean
    public RestTemplate getRestTemplate(){
        final SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setReadTimeout(120000);
        simpleClientHttpRequestFactory.setConnectTimeout(120000);
        return new RestTemplate(simpleClientHttpRequestFactory);
    }
}
