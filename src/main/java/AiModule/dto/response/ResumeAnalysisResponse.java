package AiModule.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeAnalysisResponse {
    private AtsScoreResponse ats;
    private CompatibilityResponse compatibility;
    private List<String> extractedSkills;
    private List<SuggestionResponse> suggestions;
}
