package com.sai.bookscribe.repositories;

import com.sai.bookscribe.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity,Long> {
}
