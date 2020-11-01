package commonAspect.common;

/**
 * 业务类型
 *
 * @Author yh
 * @Date 2020/9/13 18:08
 */
public enum BusinessType {

  SERVICE1("service1");


  /**
   * 业务类型的名称
   */
  private String name;

  BusinessType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
