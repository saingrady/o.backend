package configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

import util.Crypt;

@Configuration
public class LdapConfig {

    @Autowired
    private Environment environment;
    
    @Value("${ldap.secure.url}")
    private String url;

    @Value("${ldap.base.dn}")
    private String base;

    @Value("${ldap.manager.username}")
    private String user;

    @Value("${ldap.manager.password}")
    private String password;


    @Autowired
    LdapContextSource   ldapContextSource;

    // @Autowired later
    @Bean
    public LdapTemplate ldapTemplateBean() {

        System.out.println(environment.getProperty("ldap.secure.url"));
        System.out.println(environment.getProperty("ldap.base.dn"));
        System.out.println(environment.getProperty("ldap.manager.username"));
        System.out.println(environment.getProperty("ldap.manager.password"));
        System.out.println("--------------------------------------------");
        System.out.println(url);
        System.out.println(base);
        System.out.println(user);
        System.out.println(Crypt.Decrypt(password));
        System.out.println("--------------------------------------------");

        LdapTemplate ldapTemplate = new LdapTemplate(ldapContextSource);
        ldapTemplate.setIgnorePartialResultException(true);
        return ldapTemplate;
    }

    @Bean
    public LdapContextSource ldapContextSourceBean() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl(url);
        contextSource.setBase(base);
        contextSource.setUserDn(user);
        contextSource.setPassword(password);
        // contextSource.setPassword(Crypt.decrypt(env.getProperty("ldap.manager.password")));
        contextSource.setCacheEnvironmentProperties(true);
        contextSource.setPooled(true);
        contextSource.setReferral("follow");
        return contextSource;
    }
}
