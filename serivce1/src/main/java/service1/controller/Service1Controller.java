package service1.controller;

import commonAspect.aspect.MyControllerLog;
import commonAspect.common.BusinessType;
import commonAspect.common.OperateType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * @Author yh
 * @Date 2020/9/6 22:06
 */
@RestController
public class Service1Controller {


  @RequestMapping("/service1")
  @MyControllerLog(businessType = BusinessType.SERVICE1, operateType = OperateType.QUERY)
  public String service1(HttpServletResponse res,String name) {
    Collection<String> headers = res.getHeaders("CUSTOM-RESPONSE-HEADER");
    System.out.println("执行controller");
//    int i =1/0;

    return "service1";
  }
}
