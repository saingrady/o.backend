package bootstrap;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import filter.RequestProcessingTimeTraceFilter;
import filter.ThirdPartyApiKeySecretPassFilter;
import interceptor.BasicAuthenticationInterceptor;
import interceptor.CustomThrottleInterceptor;
import interceptor.OAuthAuthorizationInterceptor;
import interceptor.RequestProcessingTimeTraceInterceptor;
import interceptor.RoleAccessControlInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

// Spring Context

 @Lazy
 @EnableWebMvc
 // all controllers in one
 @EnableAutoConfiguration
 @PropertySource({"classpath:env.properties"})
 // @Import(value = {LdapConfig.class, RestTemplateConfig.class})
 // Singleton instance object, @Autowired later
 @ComponentScan(basePackages = {"configuration", "filter", "dispatcher", "interceptor", "web", "business", "persistence"})
 // @Configuration
 // web.xml

public class WebConfigAdapter extends WebMvcConfigurerAdapter {

    // Filters , to register later
    @Autowired
    private RequestProcessingTimeTraceFilter requestProcessingTimeTraceFilter;
    @Autowired
    private ThirdPartyApiKeySecretPassFilter thirdPartyApiKeySecretPassFilter;

    // Interceptors , to Register later
    @Autowired
    private CustomThrottleInterceptor             customThrottleInterceptor;
    @Autowired
    private RequestProcessingTimeTraceInterceptor requestProcessingTimeTraceInterceptor;
    @Autowired
    private BasicAuthenticationInterceptor        basicAuthenticationInterceptor;
    @Autowired
    private OAuthAuthorizationInterceptor         oAuthAuthorizationInterceptor;
    @Autowired
    private RoleAccessControlInterceptor          roleAccessControlInterceptor;

    @Bean
    public FilterRegistrationBean requestProcessingTimeTraceFilterBean() {
        FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        filterRegBean.setFilter(requestProcessingTimeTraceFilter);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");
        filterRegBean.setUrlPatterns(urlPatterns);
        filterRegBean.setOrder(1);
        return filterRegBean;
    }

    @Bean
    public FilterRegistrationBean thirdPartyApiKeySecretPassFilterBean() {
        FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        filterRegBean.setFilter(thirdPartyApiKeySecretPassFilter);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/onlythisway2/*");
        filterRegBean.setUrlPatterns(urlPatterns);
        filterRegBean.setOrder(2);
        return filterRegBean;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // FIFO
        registry.addInterceptor(customThrottleInterceptor); // all path by default
        registry.addInterceptor(requestProcessingTimeTraceInterceptor); // .addPathPatterns("/**");
        registry.addInterceptor(basicAuthenticationInterceptor);
        registry.addInterceptor(oAuthAuthorizationInterceptor);
        registry.addInterceptor(roleAccessControlInterceptor);
        
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
        // support unicode
        messageConverters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        super.configureMessageConverters(messageConverters);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/html/**").addResourceLocations("/html/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
    }

}