package commonAspect.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import commonAspect.common.LogInfo;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 发送消息的工具类
 *
 * @Author yh
 * @Date 2020/9/14 23:23
 */
@Component
public class SengMsgUtils {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  public void sendMsg(LogInfo obj) throws JsonProcessingException {


    String msgId = UUID.randomUUID().toString();

    //构建correlationData 用于做可靠性投递得,ID:必须为全局唯一的 根据业务规则
    CorrelationData correlationData = new CorrelationData(msgId);
    obj.setMsgId(msgId);
    ObjectMapper objectMapper = new ObjectMapper();

    rabbitTemplate.convertAndSend(RabbitmqExchangeQueueConfig.EXCHANGE_NAME+"a", RabbitmqExchangeQueueConfig.DIRECT_ROUTING_KEY, objectMapper.writeValueAsString(obj), correlationData);
  }


}
