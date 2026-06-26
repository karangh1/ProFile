package AiModule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiClientService {
    private final ChatClient chatClient;

    public AiClientService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String ask(String prompt) {

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}
