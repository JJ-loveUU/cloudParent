package commonAspect.aspect;

import commonAspect.common.LogInfo;
import commonAspect.common.LogStatus;
import commonAspect.rabbitmq.SengMsgUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.applet.Main;

import java.lang.reflect.Method;

/**
 * @Author yh
 * @Date 2020/9/13 11:09
 */
@Aspect
@Component
public class NotVeryUsefulAspect {

  protected static final Log log = LogFactory.getLog(NotVeryUsefulAspect.class);

  @Autowired
  private SengMsgUtils sengMsgUtils;

  @Pointcut("@annotation(commonAspect.aspect.MyControllerLog)") // the pointcut expression
  private void anyOldTransfer() {
  }
//
//
//  @Before("aspect.NotVeryUsefulAspect.anyOldTransfer()")
//  public void dobefore() {
//    System.out.println("之前做的事");
//  }
//
//  //返回结果后
//  @AfterReturning(pointcut = "aspect.NotVeryUsefulAspect.anyOldTransfer()",
//          returning = "retVal")
//  public void doAfterReturn(Object retVal) {
//    System.out.println("返回结果后:" + retVal);
//  }
//
//  @AfterThrowing(pointcut = "aspect.NotVeryUsefulAspect.anyOldTransfer()",
//          throwing = "ex")
//  public void doAfterThrowing(Exception ex) {
//    System.out.println("出现异常:" + ex.getMessage());
//  }
//
//  @After("aspect.NotVeryUsefulAspect.anyOldTransfer()")
//  //不管怎么样都会执行,用来释放资源等等...
//  public void doAfter() {
//    System.out.println("执行@after");
//  }


  @Around("commonAspect.aspect.NotVeryUsefulAspect.anyOldTransfer()")
  public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
    Object retVal = null;
    LogInfo info = null;

    try {

      retVal = pjp.proceed();
      //生成日志

      info = handle(pjp, null);
      sengMsgUtils.sendMsg(info);
      log.info("操作成功:" + info.toString());

    } catch (Throwable t) {
      info = handle(pjp, t);
      sengMsgUtils.sendMsg(info);
      log.error("操作失败:" + info.toString(), t);
      throw t;
    }
    return retVal;
  }

  /**
   * 处理访问接口的信息，推送至rabbitmq
   *
   * @param p
   * @param t
   * @return
   */
  private LogInfo handle(ProceedingJoinPoint p, Throwable t) {

    Signature signature = p.getSignature();
    MethodSignature signature1 = (MethodSignature) signature;
    Method method = signature1.getMethod();

    LogInfo info = null;



    //解析注解
    if (method.isAnnotationPresent(MyControllerLog.class)) {
      MyControllerLog annotation = method.getAnnotation(MyControllerLog.class);
      info = new LogInfo(annotation.businessType(), annotation.operateType(), "a", signature.toString());
      if (t == null) {
        info.setLogStatus(LogStatus.SUCCESS);
      } else {
        info.setLogStatus(LogStatus.FAIL);
        info.setExeceptionCause(t.getMessage());
      }
    }





    return info;

  }


  public static void main(String[] args) {
    System.out.println(77777);
  }



}
