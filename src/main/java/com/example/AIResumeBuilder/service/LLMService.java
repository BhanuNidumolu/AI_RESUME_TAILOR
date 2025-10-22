package com.example.AIResumeBuilder.service;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LLMService {

    // This is the correct architecture (injecting the model)
    private final OpenAiChatModel chatModel;

    @Autowired
    public LLMService(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String generatePrompt(List<String> resJdInstruc) {
        String resumeContent = resJdInstruc.get(0);
        String jdContent = resJdInstruc.get(1);
        String instructions = resJdInstruc.get(2);

        return createPrompt(resumeContent, jdContent, instructions);
    }

    // Your prompt is great, no changes
    private String createPrompt(String resumeContent, String jdContent, String instructions) {
        StringBuilder promptBuilder = new StringBuilder();
        // ... (all your prompt logic) ...
        promptBuilder.append("I need you to create a professional resume tailored to the job description provided below.\n\n");
        if (resumeContent != null && !resumeContent.isEmpty()) {
            promptBuilder.append("CANDIDATE RESUME:\n");
            promptBuilder.append(resumeContent).append("\n\n");
        }
        if (jdContent != null && !jdContent.isEmpty()) {
            promptBuilder.append("TARGET JOB DESCRIPTION:\n");
            promptBuilder.append(jdContent).append("\n\n");
        }
        if (instructions != null && !instructions.isEmpty()) {
            promptBuilder.append("ADDITIONAL INSTRUCTIONS:\n");
            promptBuilder.append(instructions).append("\n\n");
        }
        promptBuilder.append("OUTPUT REQUIREMENTS:\n");
        promptBuilder.append("1. Use popular resume frameworks like XYZ (Accomplished X as measured by Y by doing Z), CAR/PAR (Challenge-Action-Result), and STAR (Situation-Task-Action-Result) to craft impact-oriented bullet points.\n");
        promptBuilder.append("2. Structure the resume into clear sections: Contact Information, Professional Summary, Skills, Experience, Education, Certifications, Projects, and Additional Information.\n");
        promptBuilder.append("3. Quantify achievements with metrics (percentages, numbers, time saved) and start each bullet with strong action verbs.\n");
        promptBuilder.append("4. Tailor content by matching keywords and phrases from the job description to optimize for ATS screening.\n");
        promptBuilder.append("5. Use concise bullet points (max two lines), maintain consistent date formatting (e.g., MMM YYYY), and ensure reverse chronological order in Experience and Education.\n");
        promptBuilder.append("6. Keep the resume length appropriate: one page for early-career roles, two pages for senior positions, focusing on relevance.\n");
        promptBuilder.append("7. Ensure readability: choose standard fonts, use adequate white space, avoid graphics, tables, and complex layouts.\n");
        promptBuilder.append("8. Eliminate first-person pronouns; use clear, concise language.\n");
        promptBuilder.append("9. Provide consistent formatting for headings, bullet styles, and indentation.\n");
        promptBuilder.append("10. Include an optional 'Interests' or 'Additional Information' section to showcase cultural fit if space allows.\n\n");
        promptBuilder.append("return the generated resume in the way which i can simply copy and paste it in a word file and everything will be perfect. there should be no need to make any further changes.\n\n");

        return promptBuilder.toString();
    }


    public String createResume(String llmPrompt) throws JsonProcessingException {
        Prompt finalPrompt = new Prompt(new UserMessage(llmPrompt));
        ChatResponse response = this.chatModel.call(finalPrompt);

        return response.getResult().getOutput().getText();
    }
}

