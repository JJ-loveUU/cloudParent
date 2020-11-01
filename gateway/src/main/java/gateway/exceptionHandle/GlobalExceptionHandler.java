package gateway.exceptionHandle;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author yh
 * @Date 2020/9/12 20:13
 */
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {


  @Override
  public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
    return null;
  }
}
