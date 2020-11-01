package logs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author yh
 * @Date 2020/9/15 10:34
 */
@SpringBootApplication(scanBasePackages = {"commonAspect","logs"})
public class LogsBootApplication {


  public static void main(String[] args) {
    SpringApplication.run(LogsBootApplication.class, args);
  }
}
