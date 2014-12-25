package business;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import configuration.LdapConfig;
import configuration.RestTemplateConfig;
import util.Generator;

@Service
public class TestService {

    // from single @Bean
    @Autowired
    private RestTemplate restTemplate;

    // from single @Bean
    @Autowired
    private LdapTemplate ldapTemplate;

    public String test() {
        this.testRest();
        this.testLdap();
        return null;
    }

    public void testRest() {
        System.out.println(restTemplate);
    }

    public void testLdap() {
        System.out.println(ldapTemplate);
    }

    public String testFile() {

        Properties configProp = new Properties();
        InputStream in = ClassLoader.class.getResourceAsStream("/testFile.txt");
        try {
            configProp.load(in);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return configProp.getProperty("hello.world");
    }

    public String accessToken() {
        return Generator.accessToken();
    }

}
