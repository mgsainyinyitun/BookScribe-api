package com.sai.bookscribe.messages.page;

import com.sai.bookscribe.entities.PageEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PageCreateResponse {

    private Long id;

    private Integer number;

    private String contexts;

    private Long bookId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public PageCreateResponse(PageEntity page){
        this.id = page.getId();
        this.number = page.getPageNumber();
        this.contexts = page.getContexts();
        this.bookId = page.getBook().getId();
        this.createdAt = page.getCreatedAt();
        this.updatedAt = page.getUpdatedAt();
    }
}
