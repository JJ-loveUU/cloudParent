package commonAspect.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置rabbitmqtemplate和exchange...
 * @Author yh
 * @Date 2020/9/14 11:34
 */
@Configuration
public class RabbitmqExchangeQueueConfig {

  public static final String EXCHANGE_NAME = "springboot.direct.exchange";
  public static final String QUEUE_NAME = "directQueue";
  public static final String DIRECT_ROUTING_KEY = "direct";


  @Bean
  public DirectExchange myDirectExchange() {
    DirectExchange directExchange = new DirectExchange(EXCHANGE_NAME, true, false);
    return directExchange;
  }

  @Bean
  public Queue myDirectQueue() {
    Queue directQueue = new Queue(QUEUE_NAME, true, false, false);
    return directQueue;
  }

  @Bean
  public Binding myDirectBinding() {
    return BindingBuilder.bind(myDirectQueue()).to(myDirectExchange()).with(DIRECT_ROUTING_KEY);
  }
}
