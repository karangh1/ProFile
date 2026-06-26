package AiModule.service;

import AiModule.dto.response.ResumeAnalysisResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proFile.proFile.entity.Document;
import com.proFile.proFile.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeAnalysisService {

    private final PdfExtractionService pdfService;

    private final PromptBuilderService promptBuilder;

    private final AiClientService aiClient;

    private final DocumentRepository documentRepository;

    private final ObjectMapper mapper;

    public ResumeAnalysisResponse analyze(
            String documentId,
            String jobDescription
    ) {

        try {

            Document document =
                    documentRepository
                            .findById(documentId)
                            .orElseThrow();

            String resumeText =
                    pdfService.extractText(
                            document.getFilePath()
                    );

            String prompt =
                    promptBuilder.buildPrompt(
                            resumeText,
                            jobDescription
                    );

            String response =
                    aiClient.ask(prompt);

            return mapper.readValue(
                    response,
                    ResumeAnalysisResponse.class
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Analysis failed",
                    e
            );
        }
    }
}
