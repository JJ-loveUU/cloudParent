package service1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @Author yh
 * @Date 2020/9/6 21:59
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages={"service1","commonAspect", "other"})
@EnableAspectJAutoProxy//允许aop
public class Service1Application {

  public static void main(String[] args) {
    SpringApplication.run(Service1Application.class, args);
  }
}
