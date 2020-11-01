package logs.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import commonAspect.common.LogInfo;
import commonAspect.rabbitmq.RabbitmqExchangeQueueConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author yh
 * @Date 2020/9/14 20:46
 */
@Component
public class MyAckReceiver implements ChannelAwareMessageListener {

  @Override
  public void onMessage(Message message, Channel channel) throws Exception {
    long deliveryTag = message.getMessageProperties().getDeliveryTag();
    try {

      ObjectMapper objectMapper = new ObjectMapper();
      LogInfo logInfo = objectMapper.readValue(message.getBody(), LogInfo.class);
      //可以根据不同的队列来进行不同的逻辑操作
      if (RabbitmqExchangeQueueConfig.QUEUE_NAME.equals(message.getMessageProperties().getConsumerQueue())) {
        System.out.println("消费的消息来自的队列名为：" + message.getMessageProperties().getConsumerQueue());
        System.out.println("消费的信息是:" + logInfo);
      }


//      channel.basicAck(deliveryTag, true);


//			channel.basicReject(deliveryTag, true);//为true会重新放回队列
    } catch (Exception e) {
      channel.basicReject(deliveryTag, false);
      e.printStackTrace();
    }
  }

  //{key=value,key=value,key=value} 格式转换成map
  private Map<String, String> mapStringToMap(String str, int entryNum) {
    str = str.substring(1, str.length() - 1);
    String[] strs = str.split(",", entryNum);
    Map<String, String> map = new HashMap<String, String>();
    for (String string : strs) {
      String key = string.split("=")[0].trim();
      String value = string.split("=")[1];
      map.put(key, value);
    }
    return map;
  }
}