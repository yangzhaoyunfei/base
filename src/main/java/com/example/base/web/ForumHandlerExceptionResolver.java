package com.example.base.web;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * Description: 异常处理
 *
 * @author yangzhaoyunfei yangzhaoyunfei@qq.com
 * @date 2018/5/9
 */
public class ForumHandlerExceptionResolver extends
        SimpleMappingExceptionResolver {
    @Override
    protected ModelAndView doResolveException(
			javax.servlet.http.HttpServletRequest httpServletRequest,
			javax.servlet.http.HttpServletResponse httpServletResponse,
			Object o, Exception e) {
		httpServletRequest.setAttribute("ex", e);
		e.printStackTrace();
		return super.doResolveException(httpServletRequest,
				httpServletResponse, o, e);
	}
}
