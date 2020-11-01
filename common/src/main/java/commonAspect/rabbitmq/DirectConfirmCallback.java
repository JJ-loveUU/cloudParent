package commonAspect.rabbitmq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * broker确认收到消息回调
 *
 * @Author yh
 * @Date 2020/9/14 11:58
 */
public class DirectConfirmCallback implements RabbitTemplate.ConfirmCallback {
  protected static final Log log = LogFactory.getLog(DirectConfirmCallback.class);

  @Override
  public void confirm(CorrelationData correlationData, boolean ack, String cause) {
    //如果确认收到消息
    if (ack) {
      log.info("broker收到消息，correlationData:" + correlationData);
    } else {
      log.info("broker没有收到消息，correlationData:" + correlationData + ",原因" + cause);
    }

    //TODO

  }
}
