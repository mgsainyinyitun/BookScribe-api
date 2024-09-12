package com.sai.bookscribe.repositories;

import com.sai.bookscribe.entities.PageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageRepository extends JpaRepository<PageEntity,Long> {
}
