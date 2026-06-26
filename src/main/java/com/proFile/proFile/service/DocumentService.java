package com.proFile.proFile.service;

import com.proFile.proFile.dto.response.DocumentResponse;
import com.proFile.proFile.entity.Category;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {

    DocumentResponse upload( MultipartFile file,
                             Category category,
                             String email);

    Page<DocumentResponse> getAll(
            int page,
            int size,
            String sortBy
    );

    Page<DocumentResponse> getByCategory(
            Category category,
            int page,
            int size
    );

    Resource download(String id);

    void delete(String id);

}
