package configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Autowired
    private Environment environment;

    @Value("${app.env}")
    private String appEnv;

    @Value("${http.conn.timeout}")
    private int    connTimeout;

    @Value("${http.read.timeout}")
    private int    readTimeout;

    // @Autowired later
    @Bean
    public RestTemplate restTemplateBean() {

        System.out.println(environment.getProperty("app.env"));
        System.out.println(environment.getProperty("http.conn.timeout"));
        System.out.println(environment.getProperty("http.read.timeout"));
        System.out.println("--------------------------------------------");
        System.out.println(appEnv);
        System.out.println(connTimeout);
        System.out.println(readTimeout);
        System.out.println("--------------------------------------------");

        return new RestTemplate();

    }

}
