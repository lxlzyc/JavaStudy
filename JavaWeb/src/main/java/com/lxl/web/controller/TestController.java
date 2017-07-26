package com.lxl.web.controller;

import com.lxl.web.service.AccountService;
import com.lxl.web.util.ResultValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
  * @Description: TODO
  * @author: lxl
  * @date: 2017/7/26
  */
@Controller
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    AccountService accountService;

    //请求转发至jsp

    @RequestMapping(value = {"/web"})
    public String index() {
        return "demo";
    }

    //请求重定向至jsp
    @RequestMapping(value = {"", "/","/web", "/web/index"})
    public String redirect() {
        return "redirect:/web";
    }

    //此时返回json格式数据
    @RequestMapping(value = "/ajax/tests", method = RequestMethod.GET)
    @ResponseBody
    public ResultValue data(
            @RequestParam(value = "tempNum", required = false, defaultValue = "0") long tempNum) {
        ResultValue result = new ResultValue();
        //日志实例
        logger.info("tempNum="+tempNum);
        logger.error("error");
        result.put("tempNum", accountService.getAccountByPhone("11100000000"));
        return result;
    }


}
