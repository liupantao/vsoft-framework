package com.vsoft.framework.context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import java.util.HashMap;
import java.util.Map;

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

    public static void main(String[] args) {
        Map<String,Map<String,Map<String,String>>> map=new HashMap<>();

        Map<String,String> map3= new HashMap<>();
        Map<String,Map<String,String>> map2= new HashMap<>();
        map3.put("社会代码","123445");
        map3.put("信用代码","125333");
        map2.put("当前清单",map3);
        Map<String,String> map33= new HashMap<>();
        map33.put("社会代码1","12344444");
        map33.put("信用代码2","12533444443");
        map2.put("历史清单",map33);
        map.put("000001",map2);

        Map<String,String> m3= new HashMap<>();
        Map<String,Map<String,String>> m2= new HashMap<>();
        m3.put("社会代码","123445");
        m3.put("信用代码","125333");
        m2.put("当前清单",m3);
        Map<String,String> m33= new HashMap<>();
        m33.put("社会代码1","12344444");
        m33.put("信用代码2","12533444443");
        m2.put("历史清单",m33);
        map.put("000002",m2);

        JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(map));


        String s = itemJSONObj.toJSONString();
      //  System.out.println(s);
        String s1 = itemJSONObj.toString();
        System.out.println(s1);

        Map<String,Map<String,Map<String,String>>> itemMap = JSONObject.toJavaObject(itemJSONObj, Map.class);

        StringBuffer sb= new StringBuffer();
        sb.append(" { 100201={社会信息=234 } ,100202={社会信息=2234 }  }");

    }
}
