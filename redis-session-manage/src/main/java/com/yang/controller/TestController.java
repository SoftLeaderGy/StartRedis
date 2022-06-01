package com.yang.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/07/20:33
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Value("${server.port}")
    private String port;
    /**
     * 使用redis的session管理  注意：当session中的数据发生变化是必须将session中变化的数据同步到redis中
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("端口------------------------->"+ port);
        List<String> list = (List<String>)request.getSession().getAttribute("list");

        if(list == null){
            list = new ArrayList<>();
        }
        list.add("XXXXX");
        request.getSession().setAttribute("list",list); // 每一次session变化都要同步

        response.getWriter().println("size: "+ list.size());
        response.getWriter().println("sessionid: " + request.getSession().getId());
    }
    @RequestMapping("/remove")
    void removeSessionAttribute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("sessionID: "+ request.getSession().getId());
        request.getSession().removeAttribute("list");
    }

    /**
     * 退出登陆，并会将redis的session也清除
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/logout")
    void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 退出登陆
        request.getSession().invalidate(); // 注销session 使其失效
        response.getWriter().println("退出登录");
    }


    @RequestMapping("/port")
    String port(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return port;
    }
}
