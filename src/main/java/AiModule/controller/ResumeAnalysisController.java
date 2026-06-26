package AiModule.controller;

import AiModule.dto.request.ResumeAnalysisRequest;
import AiModule.dto.response.ResumeAnalysisResponse;
import AiModule.service.ResumeAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analysis")
@RequiredArgsConstructor
public class ResumeAnalysisController {
    private final ResumeAnalysisService analysisService;

    @PostMapping("/{documentId}")
    public ResumeAnalysisResponse analyze(
            @PathVariable String documentId,
            @RequestBody ResumeAnalysisRequest request
    ) {

        return analysisService.analyze(
                documentId,
                request.getJobDescription()
        );
    }
}
