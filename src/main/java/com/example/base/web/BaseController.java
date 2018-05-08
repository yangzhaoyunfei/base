package com.example.base.web;

import com.example.base.cons.CommonConstant;
import com.example.base.domain.User;
import com.github.pagehelper.Page;
import com.zjbdos.appbase.util.PageUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 所有Controller的基类,
 * 提供其他controller共有的一些方法,所有具体的controller都继承于这个controller,并定义自己的请求方法
 */
public class BaseController<T> {
    protected static final String ERROR_MSG_KEY = "errorMsg";


    /**
     * 获取保存在Session中的用户对象
     *
     * @param request
     * @return
     */
    protected User getSessionUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(
                CommonConstant.USER_CONTEXT);
    }

    /**
     * 保存用户对象到Session中
     *
     * @param request
     * @param user
     */
    protected void setSessionUser(HttpServletRequest request, User user) {
        request.getSession().setAttribute(CommonConstant.USER_CONTEXT,
                user);
    }


    /**
     * 获取基于应用程序的url绝对路径
     *
     * @param request
     * @param url     以"/"打头的URL地址
     * @return 基于应用程序的url绝对路径
     */
    public final String getAppbaseUrl(HttpServletRequest request, String url) {
        Assert.hasLength(url, "url不能为空");
        Assert.isTrue(url.startsWith("/"), "必须以/打头");
        return request.getContextPath() + url;
    }


    /**
     * 用于列表页,AJAX分页获取
     *
     * @param request 要求携带必须携带 companyId
     * @return
     */
    @RequestMapping(value = {"/T/listJson"})
    @ResponseBody
    public PageUtils reportListJson(HttpServletRequest request) {

        Map map = ParamUtils.getParamMap(request);

        Page<T> pos = reportService.selectPageList(map);

        PageUtils page = new PageUtils(pos.getResult(), (int) pos.getTotal());

        return page;
    }
}
