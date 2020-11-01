package gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import commonAspect.common.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author yh
 * @Date 2020/9/9 16:15
 */
@Component
public class CustomerGlobalFilter implements GlobalFilter, Ordered {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    ServerHttpResponse response = exchange.getResponse();
    ServerHttpRequest request = exchange.getRequest();

    //判断请求头中是否有Authorization
    String authorization = request.getHeaders().getFirst("Authorization");
    if (StringUtils.isNotBlank(authorization)) {


      int i=1/0;
      //如果存在则执行下一个filter
      return chain.filter(exchange);
    } else {
      response.getHeaders().add("Content-Type", "application/json; charset=utf-8");
      response.setStatusCode(HttpStatus.OK);
      Result result = Result.fail("不存在令牌", null);
      DataBuffer data = response.bufferFactory().wrap(JSONObject.toJSONString(result).getBytes());
      //如果不存在则直接响应，不执行下面的filter
      return response.writeWith(Mono.just(data));
    }
  }

  @Override
  //@Order来指定执行的顺序，数字越小，优先级越高
  public int getOrder() {
    return 1;
  }
}
