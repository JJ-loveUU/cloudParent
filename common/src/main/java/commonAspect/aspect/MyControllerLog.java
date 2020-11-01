package commonAspect.aspect;

import commonAspect.common.BusinessType;
import commonAspect.common.OperateType;

import java.lang.annotation.*;

/**
 * @Author yh
 * @Date 2020/9/13 18:05
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
@Documented
public @interface MyControllerLog {

  BusinessType businessType();

  OperateType operateType();

}
