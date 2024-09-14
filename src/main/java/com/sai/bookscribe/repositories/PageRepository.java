package com.sai.bookscribe.repositories;

import com.sai.bookscribe.entities.PageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PageRepository extends JpaRepository<PageEntity,Long> {
    Optional<PageEntity> findByPageNumber(Integer pageNumber);

    Optional<PageEntity> findByPageNumberAndBookId(Integer pageNumber, Long bookId);
}
