package com.example.base.web;

import com.smart.domain.User;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.smart.cons.CommonConstant.LOGIN_TO_URL;
import static com.smart.cons.CommonConstant.USER_CONTEXT;

/**
 * 过滤器,因为有些资源需要登录才能访问,所以我们需要判断用户访问的是那种资源,以及用户的登录状态,所以需要加一个过滤器
 */
public class ForumFilter implements Filter {

    /**
     *
     */
    private static final String FILTERED_REQUEST = "@@session_context_filtered_request";

    /**
     * 不需要登录即可访问的URI资源
     */
    private static final String[] INHERENT_ESCAPE_URIS = {"/index.jsp",
            "/index.html", "/login.jsp", "/login/doLogin.html",
            "/register.jsp", "/register.html", "/board/listBoardTopics-",
            "/board/listTopicPosts-"};

    /**
     * 执行过滤
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // ②-1 保证该[[过滤器]]在一次请求中只被调用一次
        if (request != null && request.getAttribute(FILTERED_REQUEST) != null) {

            //如果该过滤器的过滤标识不为空,则调用过滤链的下个一过滤器
            chain.doFilter(request, response);

        } else {

            // ②-2 设置过滤标识,防止一次请求多次过滤
            request.setAttribute(FILTERED_REQUEST, Boolean.TRUE);
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            User userContext = getSessionUser(httpRequest);

            // ②-3 用户未登录, 且当前URI资源需要登录才能访问
            if (userContext == null && !isURILogin(httpRequest.getRequestURI(), httpRequest)) {

                //获取用户访问的url的requesturl
                String toUrl = httpRequest.getRequestURL().toString();

                //获取queryurl
                if (!StringUtils.isEmpty(httpRequest.getQueryString())) {
                    toUrl += "?" + httpRequest.getQueryString();
                    /**拼接用户请求的url字符串,就是get请求?后的那些,也就是说,请求路径分为两部分,一部分是requesturl,一部分是queryurl*/
                }

                // ②-4将用户的请求URL保存在session中,用于登录成功之后,跳到目标URL
                httpRequest.getSession().setAttribute(LOGIN_TO_URL, toUrl);

                // ②-5转发到登录页面
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }

            //调用过滤链上的下一个过滤器
            chain.doFilter(request, response);
        }
    }


    /**
     * 需要实现的过滤器初始化方法
     *
     * @param filterConfig 过滤器配置对象
     * @throws ServletException
     */
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * 当前URI资源是否需要登录才能访问
     *
     * @param requestURI
     * @param request
     * @return
     */
    private boolean isURILogin(String requestURI, HttpServletRequest request) {

        //从请求中获取路径,忽略大小写,比较请求路径,
        if (request.getContextPath().equalsIgnoreCase(requestURI) || (request.getContextPath() + "/").equalsIgnoreCase(requestURI)) {
            return true;
        }

        //迭代数组,进行比对
        for (String uri : INHERENT_ESCAPE_URIS) {
            if (requestURI != null && requestURI.contains(uri)) {
                return true;
            }
        }

        return false;
    }

    protected User getSessionUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(USER_CONTEXT);
    }


    /**
     * 需要实现的过滤器销毁方法
     */
    public void destroy() {
    }
}
