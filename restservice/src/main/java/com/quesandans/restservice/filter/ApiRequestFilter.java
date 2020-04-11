package com.quesandans.restservice.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiRequestFilter implements Filter {



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String origin = request.getHeader("ORIGIN");
            String headers = request.getHeader("Access-Control-Request-Headers");
            String methods = request.getHeader("Access-Control-Request-Method");
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setHeader("Access-Control-Allow-Methods", methods);
            response.setHeader("Access-Control-Allow-Headers", headers);
            response.setHeader("Access-Control-Max-Age", "1728000");
            return;
        }
        HttpServletResponse response = ((HttpServletResponse) servletResponse);
        response.setHeader("Access-Control-Allow-Origin", "*");
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
