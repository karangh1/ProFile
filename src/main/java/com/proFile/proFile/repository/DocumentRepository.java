package com.proFile.proFile.repository;

import com.proFile.proFile.entity.Category;
import com.proFile.proFile.entity.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document,String> {

    Page<Document> findByCategory(Category category, Pageable pageable);
}
