package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class CustomThrottleInterceptor extends HandlerInterceptorAdapter {

    // Handle to Denial-of-service attack
    // 300 API calls in 15 minutes
    private int                 perMinutes  = 15;
    private int                 perRequests = 300;

    public int getPerMinutes() {
        return perMinutes;
    }

    public void setPerMinutes(int perMinutes) {
        this.perMinutes = perMinutes;
    }

    public int getPerRequests() {
        return perRequests;
    }

    public void setPerRequests(int perRequests) {
        this.perRequests = perRequests;
    }

    // TODO
    private static final Logger logger = LoggerFactory.getLogger(CustomThrottleInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandle----------------------------Throttle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        logger.info("postHandle----------------------------Throttle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        logger.info("afterCompletion----------------------------Throttle");
    }
}
