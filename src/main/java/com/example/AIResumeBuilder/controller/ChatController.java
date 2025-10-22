package com.example.AIResumeBuilder.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/llm")
public class ChatController{

    @Autowired
    private final OpenAiChatModel chatModel;

    public ChatController(OpenAiChatModel chatModel){
        this.chatModel = chatModel;
    }

    @GetMapping("/generate")
    public Map<String, Object> generate(@RequestParam String prompt) throws JsonProcessingException {
        Prompt finalPrompt = new Prompt(new UserMessage(prompt));
        ChatResponse response = this.chatModel.call(finalPrompt);

        return Map.of("text", response.getResult().getOutput().getText());
    }
}
