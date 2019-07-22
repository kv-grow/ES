package infrastructure.configuration;

import com.google.gson.GsonBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import app.services.ESEngine;
import app.services.ESParameters;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class MainConfig {

    @Value("${es.domain}")
    private String domain;
    @Value("${es.index}")
    private String index;
    @Value("${es.type}")
    private String type;
    @Value("${es.resultsize}")
    private int resultSize;


    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/api.*"))
                .build();
    }

    @Bean
    public ESParameters esParameters() {
        return new ESParameters(index, type, resultSize);
    }

    @Bean
    public ESEngine esEngine(){
        return new ESEngine(esClient(), esParameters());
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("ES REST API")
                .description("ES REST API Swagger")
                .version("1.0")
                .build();
    }

    private JestClient esClient() {
        final JestClientFactory clientFactory = new JestClientFactory();
        final HttpClientConfig httpClientConfig = new HttpClientConfig.Builder(domain)
                .multiThreaded(true)
                .gson(new GsonBuilder().create())
                .build();
        clientFactory.setHttpClientConfig(httpClientConfig);
        return clientFactory.getObject();
    }



}
