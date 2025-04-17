package CommunityChat;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // WebSocket 메세지 기능(STOMP 포함)을 활성화 한다는 뜻
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    //메세지를 받을 경로 설정
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/queue"); //구독용 - 해당 주소를 구독하고 있는 클라이언트에게 메세지 전달
        config.setApplicationDestinationPrefixes("/app"); //발신용 - 클라이언트가 보낸 메세지 받을 prefix
//        config.setUserDestinationPrefix("/user"); // 쪽지용

        // queue가 일대일 채팅인 이유 : stomp메시징에서 일반적으로 사용되는 관례
        // topic : 여러 클라이언트에게 메세지 브로드캐스트할때
    }



    //클라이언트가 연결할 엔드포인트 등록
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")  //SocJS 연결 주소
                .setAllowedOrigins("*")
                .withSockJS();  //버전 낮은 브라우저에서도 적용 가능(fallback 옵셥)

        //주소: ws:localhost:8080/ws
    }


}
