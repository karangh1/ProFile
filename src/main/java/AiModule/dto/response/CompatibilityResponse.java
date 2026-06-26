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
public class CompatibilityResponse {
    private Integer percentage;

    private List<String> matchedSkills;

    private List<String> missingSkills;
}
