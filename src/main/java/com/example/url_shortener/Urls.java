package com.example.url_shortener;
import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Data
@Table(name = "urls")
public class Urls {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = " short_code",unique = true,nullable = false,length = 6)
    private String shortCode;

    @Column(name = "original_url",nullable = false)
    private String originalCode;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "click_count")
    private int count =0;

    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
    }

}
