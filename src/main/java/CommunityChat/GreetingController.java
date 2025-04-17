package CommunityChat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

//Message-handling Controller
@RestController
public class GreetingController {

    //sendto 1:n으로 메세지 보낼때
    //SendToUser 1:1로 베세지 보낼때 사용, 경로 /queue 시작

    @MessageMapping("/chat")
    @SendTo("/topic/message")
    public ChatMessage handleMessage(ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}
