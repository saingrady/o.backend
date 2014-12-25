package filter;

import java.io.IOException;

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

@Component
// @Order(1)
public class RequestProcessingTimeTraceFilter implements Filter, ServletContextInitializer {

    FilterConfig filterConfig = null;

	@Override
	public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
        System.out.println("init -------------------RequestProcessingTimeTraceFilter");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> before doFilter -------------------RequestProcessingTimeTraceFilter");
        long service_Start = System.currentTimeMillis();

        // STOP here or GO next
        chain.doFilter(request, response);
        // Do something after
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<< after doFilter -------------------RequestProcessingTimeTraceFilter");

        long service_Stop = System.currentTimeMillis();
        long serviceTime = (service_Stop - service_Start);
        String path = ((HttpServletRequest) request).getRequestURI();
        filterConfig.getServletContext().log(
                "Time taken to process request for: " + path + " is: " + serviceTime + " milliseconds");

	}

	@Override
	public void destroy() {
        System.out.println("destroy -------------------RequestProcessingTimeTraceFilter");
        this.filterConfig = null;
		
	}

    @Override
    public void onStartup(ServletContext arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
