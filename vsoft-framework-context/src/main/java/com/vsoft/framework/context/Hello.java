package com.vsoft.framework.context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.DeferredLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class Hello {

    private final static Log logger = LogFactory.getLog(ModuleFactory.class);
    private static final DeferredLog log= new DeferredLog();
    @RequestMapping("hello")
    public String hello(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ipAddr ="123";
        //Sheet
        logger.info("在线人数＝ip " + ipAddr);
        //获取
      log.info("-----------******");
        return "你嗯嗯好！";
    }
}
