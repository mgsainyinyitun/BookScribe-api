package com.sai.bookscribe.repositories;

import com.sai.bookscribe.constants.BookTypes;
import com.sai.bookscribe.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity,Long> {
    List<BookEntity> findByBookType(BookTypes bookType);
}
