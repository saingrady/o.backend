package filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import util.Utils;

@Component
// @Order(2)
public class ThirdPartyApiKeySecretPassFilter implements Filter, ServletContextInitializer {

	private String pattern;
	
    @Override
    public void init(FilterConfig config) throws ServletException {
        // TODO Auto-generated method stub
        System.out.println("init -------------------ThirdPartyApiKeySecretPassFilter");
        this.pattern = config.getInitParameter("pattern");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        // TODO Auto-generated method stub
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> before doFilter -------------------ThirdPartyApiKeySecretPassFilter");
        
        
        // Get the IP address of client machine.   
        String ipAddress = request.getRemoteAddr();

        // Log the IP address and current timestamp.
        System.out.println("------------------------------------ IP "+ ipAddress + ", Time "
                                         + new Date().toString());
        
        if (Utils.isRequestMatchPattern(pattern, request)){
        	System.out.println("----------------------Matched--------------------");
        }
        
        // STOP here or GO next
        chain.doFilter(request, response);
        // Do something after
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<< after doFilter -------------------ThirdPartyApiKeySecretPassFilter");

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        System.out.println("destroy -------------------ThirdPartyApiKeySecretPassFilter");

    }

    @Override
    public void onStartup(ServletContext arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
