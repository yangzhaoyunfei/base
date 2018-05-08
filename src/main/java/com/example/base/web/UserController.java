
package com.example.base.web;

import com.example.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


/**
 * Description: 用户控制器
 *
 * @author yangzhaoyunfei yangzhaoyunfei@qq.com
 * @date 2018/5/9
 */
@Controller
public class UserController extends BaseController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }


}
