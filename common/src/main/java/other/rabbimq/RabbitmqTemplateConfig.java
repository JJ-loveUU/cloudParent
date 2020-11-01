package other.rabbimq;

import commonAspect.rabbitmq.DirectConfirmCallback;
import commonAspect.rabbitmq.DirectReturnCallback;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq生产者发送消息配置template信息
 * @Author yh
 * @Date 2020/9/15 10:44
 */
@Configuration
public class RabbitmqTemplateConfig {


  @Bean
  public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate();
    rabbitTemplate.setConnectionFactory(connectionFactory);
    //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
    rabbitTemplate.setMandatory(true);

    rabbitTemplate.setConfirmCallback(new DirectConfirmCallback());

    rabbitTemplate.setReturnCallback(new DirectReturnCallback());
    return rabbitTemplate;
  }
}
