package commonAspect.common;

import lombok.Data;

/**
 * @Author yh
 * @Date 2020/9/9 16:41
 */
@Data
public class Result<T> {

  private String status = "200";
  private String msg = "";
  private T data;

  public Result(String status, String msg, T data) {
    this.status = status;
    this.msg = msg;
    this.data = data;
  }

  public Result() {

  }

  public void setData(T t) {
    this.data = t;
  }

  public static <E> Result success(String msg, E data) {

    return new Result("200", msg, data);

  }
  public static <E> Result fail(String msg, E data) {

    return new Result("400", msg, data);

  }

  @Override
  public String toString() {
    return "Result{" +
            "status='" + status + '\'' +
            ", msg='" + msg + '\'' +
            ", data=" + data +
            '}';
  }
}
