package com.proFile.proFile.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String fileName;

    private String fileType;

    private Long fileSize;

    @Enumerated(EnumType.STRING)
    private Category category;

    private LocalDateTime uploadDate;

    private String filePath;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
