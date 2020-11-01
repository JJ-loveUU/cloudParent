package commonAspect.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 日志信息
 *
 * @Author yh
 * @Date 2020/9/13 21:10
 */
@Data
public class LogInfo implements Serializable {
  long id;
  BusinessType businessType;
  OperateType operateType;
  String username;

  /**
   * 方法的全限定名
   */
  String qualifiedName;

  /**
   * 日志状态信息
   */
  LogStatus logStatus;

  /**
   * 异常原因
   */
  String execeptionCause;

  /**
   * 标记消息的唯一id
   */
  String msgId;

  public LogInfo() {
  }

  public LogInfo(BusinessType businessType, OperateType operateType, String username, String qualifiedName) {
    this.businessType = businessType;
    this.operateType = operateType;
    this.username = username;
    this.qualifiedName = qualifiedName;
  }

}
