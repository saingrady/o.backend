package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class RequestProcessingTimeTraceInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(RequestProcessingTimeTraceInterceptor.class);
    private static final org.apache.log4j.Logger loggerLog4j = org.apache.log4j.Logger
                                                                     .getLogger(RequestProcessingTimeTraceInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        logger.info("preHandle----------------------------Request URL::" + request.getRequestURL().toString() + ":: Start Time="
                + System.currentTimeMillis());
        // TODO
        // cannot see
        // loggerLog4j.debug("testing...");
        request.setAttribute("startTime", startTime);
        // if returned false, we need to make sure 'response' is sent

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        logger.info("postHandle----------------------------Request URL::" + request.getRequestURL().toString()
                + " Sent to Handler :: Current Time="
                + System.currentTimeMillis());
        // we can add attributes in the modelAndView and use that in the view page
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        logger.info("afterCompletion----------------------------Request URL::" + request.getRequestURL().toString()
                + ":: End Time=" + System.currentTimeMillis() + ":: Time Taken=" + (System.currentTimeMillis() - startTime));
    }

}