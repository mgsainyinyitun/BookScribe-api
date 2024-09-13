package com.sai.bookscribe.messages.book;

import com.sai.bookscribe.constants.BookTypes;
import com.sai.bookscribe.entities.BookEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookCreateResponse {

    private Long id;

    private String username;

    private BookTypes type;

    private Integer noOfPage;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public BookCreateResponse(BookEntity book){
        this.id = book.getId();
        this.username = book.getUser().getUsername();
        this.type = book.getBookType();
        this.noOfPage = book.getNumberOfPage();
        this.createdAt = book.getCreatedAt();
        this.updatedAt = book.getUpdatedAt();
    }

}
