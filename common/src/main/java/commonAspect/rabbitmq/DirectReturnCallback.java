package commonAspect.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import commonAspect.common.LogInfo;
import lombok.SneakyThrows;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.annotation.Order;

/**
 * 消息到达交换机，但是从交换机到队列失败时调用
 *
 * @Author yh
 * @Date 2020/9/14 12:18
 */
public class DirectReturnCallback implements RabbitTemplate.ReturnCallback {

  protected static final Log log = LogFactory.getLog(DirectReturnCallback.class);

  @SneakyThrows
  @Override
  public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
    String correlationId = message.getMessageProperties().getHeader("spring_returned_message_correlation");

    ObjectMapper objectMapper = new ObjectMapper();
    LogInfo loginfo = objectMapper.readValue(message.getBody(), LogInfo.class);
    log.info("correlationId:" + correlationId);
    log.info("message:" + loginfo);
    log.info("replyCode:" + replyCode);
    log.info("replyText:" + replyText);
    log.info("exchange:" + exchange);
    log.info("routingKey:" + routingKey);
  }
}
