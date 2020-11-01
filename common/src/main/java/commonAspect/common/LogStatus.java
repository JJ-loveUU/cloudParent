package commonAspect.common;

/**
 * 日志状态
 * @Author yh
 * @Date 2020/9/13 21:18
 */
public enum LogStatus {

  SUCCESS("成功"),
  FAIL("失败");

  private String name;

  LogStatus(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
