package com.proFile.proFile.dto.response;


import com.proFile.proFile.entity.Category;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentResponse {
    private String id;
    private String fileName;
    private String fileType;
    private Long fileSize;
    private Category category;
    private LocalDateTime uploadDate;
}
