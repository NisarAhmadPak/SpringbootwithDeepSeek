package com.tutorial.ai.demo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class ChatController {
    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientB) {
        this.chatClient = chatClientB.build();
    }

    @GetMapping("/{question}")
    public ResponseEntity<String, HttpStatus> getAnswerFromAIForGivenQuestion(@PathVariable String question){
        var response = chatClient
                .prompt(question)
                .call()
                .content();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
