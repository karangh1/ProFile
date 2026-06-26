package com.proFile.proFile.service;

import com.proFile.proFile.config.FileUtil;
import com.proFile.proFile.dto.response.DocumentResponse;
import com.proFile.proFile.entity.Category;
import com.proFile.proFile.entity.Document;
import com.proFile.proFile.entity.User;
import com.proFile.proFile.repository.DocumentRepository;
import com.proFile.proFile.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;

    private final String UPLOAD_DIR = "uploads/";

    @Override
    public DocumentResponse upload(MultipartFile file, Category category, String email) {

        try{
            User user = userRepository.findByEmail(email).orElseThrow();

            String filePath = FileUtil.saveFile( UPLOAD_DIR, file );

            Document doc= Document.builder()
                    .fileName(file.getOriginalFilename())
                    .fileType(file.getContentType())
                    .fileSize(file.getSize())
                    .category(category)
                    .uploadDate(LocalDateTime.now())
                    .filePath(filePath)
                    .user(user)
                    .build();

            documentRepository.save(doc);

            return map(doc);

        } catch (Exception e) {
            throw new RuntimeException("Upload fail");
        }
    }

    @Override
    public Page<DocumentResponse> getAll(int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());

        return documentRepository.findAll(pageable).map(this::map);

    }

    @Override
    public Page<DocumentResponse> getByCategory(Category category, int page, int size) {
        Pageable pageable =
                PageRequest.of(page, size);

        return documentRepository
                .findByCategory(
                        category,
                        pageable
                )
                .map(this::map);
    }

    @Override
    public Resource download(String id) {

        try {

            Document doc =
                    documentRepository
                            .findById(id)
                            .orElseThrow();

            Path path =
                    Paths.get(
                            doc.getFilePath()
                    );

            return new UrlResource(
                    path.toUri()
            );

        } catch (
                MalformedURLException e
        ) {
            throw new RuntimeException(
                    "Download Failed"
            );
        }
    }

    @Override
    public void delete(String id) {
        Document doc =
                documentRepository
                        .findById(id)
                        .orElseThrow();

        try {
            Files.deleteIfExists(
                    Paths.get(
                            doc.getFilePath()
                    )
            );
        } catch (Exception e) {}

        documentRepository.delete(doc);
    }

    private DocumentResponse map(
            Document doc
    ) {
        return DocumentResponse.builder()
                .id(doc.getId())
                .fileName(doc.getFileName())
                .fileType(doc.getFileType())
                .fileSize(doc.getFileSize())
                .category(doc.getCategory())
                .uploadDate(doc.getUploadDate())
                .build();
    }
}
