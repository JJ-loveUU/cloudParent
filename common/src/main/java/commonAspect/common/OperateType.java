package commonAspect.common;

/**
 * 业务类型的操作类型
 *
 * @Author yh
 * @Date 2020/9/13 18:12
 */
public enum OperateType {

  QUERY("查询");


  /**
   * 业务类型的操作
   */
  private String name;

  OperateType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

}
