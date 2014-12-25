package util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class Utils {

	// Manually check pattern
	public static boolean isRequestMatchPattern(String pattern, ServletRequest request){
        // check whether we have a httpServletRequest and a pattern
        if (pattern != null && request instanceof HttpServletRequest) {
            // resolve the query string from the httpServletRequest
            String queryString = ((HttpServletRequest) request).getQueryString();
            // check whether a query string exists and matches the given pattern
            if (queryString != null && queryString.matches(pattern)) {
                // TODO do someting special
            	return true;
            }
        }
        return false;
	}
}
