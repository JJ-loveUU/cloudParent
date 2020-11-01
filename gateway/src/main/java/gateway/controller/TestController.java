package gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yh
 * @Date 2020/9/6 20:21
 */
@RestController
public class TestController {

  @RequestMapping("/test")
  public String test() {
    return "aaa";
  }

}
