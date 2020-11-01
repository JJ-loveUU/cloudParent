package gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author yh
 * @Date 2020/9/7 7:30
 */
@Configuration
public class Config {

  @Bean
  public GlobalFilter customFilter() {
    return new CustomGlobalFilter();
  }

  public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
      String gatewayRequestUrlAttr = ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
      exchange.getResponse();
      return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
      return -1;
    }
  }


}
