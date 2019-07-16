package br.com.softplan.cadastropf.security.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Simple CORS filter that autorizes all origins. Because Undertow don't have
 * one.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCORSFilter implements Filter {

    /** {@inheritDoc}} */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /** {@inheritDoc}} */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        httpResponse.setHeader("Access-Control-Max-Age", "3600");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Authorization, Origin, x-requested-with, Content-Type, Accept");
        if("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())){
            httpResponse.setStatus(HttpServletResponse.SC_OK);
        }else{
            chain.doFilter(request, response);
        }
    }

    /** {@inheritDoc}} */
    @Override
    public void destroy() {}

}
