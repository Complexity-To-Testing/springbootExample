package com.scrapy.helloscrapy.controller.test;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.scrapy.helloscrapy.common.RedisUtil;
import com.scrapy.helloscrapy.service.TestService.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.UUID;

@RequestMapping(value = "/test/")
@RestController
public class MyTestController {
    @Autowired
    TestService testService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/testFunction")
    APIResponse test(){
        return new APIResponse("200",testService.test(),APIResponse.SUCCESS);
    }

    /**
     * 这里为了能简单在浏览器响应，暂时使用GET请求，
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(HttpServletRequest request){
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        if ("admin".equals(account) && "123456".equals(password)){
            /*如果已经存在Session的话，直接返回它；没有就创建一个，再返回
             * 当然Session是自动放在response中的Header中的，这里不用做其他处理*/
            request.getSession();
        }else {
            return "failed";
        }

        //生成uuid
        String uuid = UUID.randomUUID().toString().replace("-","");
        HashMap<String, Object> hmap = new HashMap<String, Object>();
        hmap.put("testKey","testValue");
        redisUtil.set(uuid,hmap);
        redisUtil.expire(uuid,600);
        HashMap<String, Object> myMap = (HashMap<String, Object>) redisUtil.get(uuid);
        System.out.print("==============="+uuid+"===============");
        return "success";
    }

    /**
     * 判断用户的session是否有效（在同一个浏览器中，同一个域中，每次Request请求，都会带上Session）
     * @param request
     * @return
     */
    @RequestMapping(value = "isValid",method = RequestMethod.GET)
    public String isSessionValid(HttpServletRequest request){
        //简化if-else表达式（其实很多地方可以简化的，这里为了方便新手朋友可以看得顺畅点，我尽量不简化）
        return request.isRequestedSessionIdValid() ? "ok":"no";
    }

    /**
     * 注销登录
     * @param session
     * @return
     */
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();//使Session变成无效，及用户退出
        return "logout";
    }
}