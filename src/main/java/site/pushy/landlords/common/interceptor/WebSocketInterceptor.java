package site.pushy.landlords.common.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import site.pushy.landlords.common.util.JWTUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * @author Fuxing
 * @since 2019/1/2 20:17
 */
public class WebSocketInterceptor implements HandshakeInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketInterceptor.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {
        // 将ServerHttpRequest转换成request请求相关的类，用来获取request域中的用户信息
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpServletRequest httpRequest = servletRequest.getServletRequest();
            String token = getTokenValue(httpRequest);
            if (!StringUtils.hasLength(token)) {
                return false;
            }

            // 解密token，拿到用户的userId
            try {
                String userId = String.valueOf(JWTUtil.decode(token));
                attributes.put("userId", userId);
            } catch (Exception e) {
                logger.error("WebSocket 连接失败, token: {}", token, e);
                return false;
            }
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }

    private String getTokenValue(HttpServletRequest httpRequest) {
        String qs = httpRequest.getQueryString();
        return qs.substring(6);
    }
}
