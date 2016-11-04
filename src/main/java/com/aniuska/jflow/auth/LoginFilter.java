package com.aniuska.jflow.auth;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hectorvent@gmail.com
 *
 */
@WebFilter(filterName = "AppFilter", urlPatterns = {"*.xhtml"})
public class LoginFilter implements Filter {

    private final Set<String> staticAllow = new HashSet();

    @Inject
    AuthenticationBean authBean;

    public LoginFilter() {
        staticAllow.add("javax.faces.resource");
        staticAllow.add("/public/");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        String reqURI = req.getRequestURI();
        String contextPath = req.getContextPath();

        for (String p : staticAllow) {
            if (reqURI.contains(p)) {
                chain.doFilter(request, response);
                return;
            }
        }

        if (reqURI.contains("login.xhtml")) {
            if (authBean != null && authBean.isLoggedIn()) {
                res.sendRedirect(contextPath + "/app/index.xhtml");
                return;
            } else {
                chain.doFilter(request, response);
                return;
            }
        }

        if (authBean == null || !authBean.isLoggedIn()) {
            res.sendRedirect(contextPath + RedirectPath.LOGIN);
            return;
        }

        if (!reqURI.contains("index.xhtml")) {
            res.sendRedirect(contextPath + "/app/index.xhtml");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        // Nothing to do here!
    }

    @Override
    public void destroy() {
        // Nothing to do here!
    }

}
