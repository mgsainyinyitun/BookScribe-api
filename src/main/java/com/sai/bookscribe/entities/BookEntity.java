package com.sai.bookscribe.entities;

import com.sai.bookscribe.constants.BookTypes;
import com.sai.bookscribe.constants.ShelfId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private BookTypes bookType;

    @Column(name = "number_of_page")
    private Integer numberOfPage;

    @Enumerated(EnumType.STRING)
    @Column(name = "shelf",nullable = true)
    private ShelfId shelf=ShelfId.SHELF_MIDDLE;

    @OneToMany(mappedBy = "book",fetch = FetchType.EAGER)
    private Set<PageEntity> pages;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, updatable = true)
    private LocalDateTime updatedAt;
}
