package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/* 
 * Client Credentials, window.btoa("id:secret"), basic, very basic
 * id secret expiredmode:LAST_ACCESS/AFTER_LOGIN expiredcountdown:TimeUnit.HOURS.toSeconds(4L)/TimeUnit.DAYS.toSeconds(60L)
 * client type backoffice 4 hours
 * client type webapp 4 hours
 * client type android 60 days
 * client type iOS 60 days
 */
@Component
public class BasicAuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Value("${basic.username}")
    private String              username;

    @Value("${basic.password}")
    private String              password;

    @Value("${basic.namespace}")
    private String              namespace;

    // TODO
    private static final Logger logger = LoggerFactory.getLogger(BasicAuthenticationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandle----------------------------Basic");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        logger.info("postHandle----------------------------Basic");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        logger.info("afterCompletion----------------------------Basic");
    }
}
