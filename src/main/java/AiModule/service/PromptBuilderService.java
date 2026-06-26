package AiModule.service;

import org.springframework.stereotype.Service;

@Service
public class PromptBuilderService {
    public String buildPrompt(
            String resume,
            String jd
    ) {

        return """
        Analyze the resume against the job description.
        
        Return ONLY valid JSON.
        
        {
          "atsScore":0,
          "summary":"",
          "compatibilityPercentage":0,
          "matchedSkills":[],
          "missingSkills":[],
          "extractedSkills":[],
          "suggestions":[
              {
                 "title":"",
                 "description":""
              }
          ]
        }
        
        Resume:
        %s
        
        Job Description:
        %s
        """.formatted(resume, jd);
    }
}
