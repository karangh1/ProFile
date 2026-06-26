package com.proFile.proFile.controller;

import com.proFile.proFile.dto.response.DocumentResponse;
import com.proFile.proFile.entity.Category;
import com.proFile.proFile.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/upload")
    public ResponseEntity<DocumentResponse>
    upload(
            @RequestParam MultipartFile file,
            @RequestParam Category category,
            Authentication auth
    ) {

        return ResponseEntity.ok(
                documentService.upload(
                        file,
                        category,
                        auth.getName()
                )
        );
    }

    @GetMapping
    public ResponseEntity<Page<DocumentResponse>>
    getAll(
            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "uploadDate")
            String sortBy
    ) {

        return ResponseEntity.ok(
                documentService.getAll(
                        page,
                        size,
                        sortBy
                )
        );
    }

    @GetMapping("/category")
    public ResponseEntity<Page<DocumentResponse>>
    byCategory(
            @RequestParam Category type,
            @RequestParam(defaultValue = "0")
            int page,
            @RequestParam(defaultValue = "5")
            int size
    ) {

        return ResponseEntity.ok(
                documentService.getByCategory(
                        type,
                        page,
                        size
                )
        );
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource>
    download(
            @PathVariable String id
    ) {

        Resource resource =
                documentService.download(id);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment"
                )
                .body(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    delete(
            @PathVariable String id
    ) {

        documentService.delete(id);

        return ResponseEntity.ok(
                "Deleted Successfully"
        );
    }
}
