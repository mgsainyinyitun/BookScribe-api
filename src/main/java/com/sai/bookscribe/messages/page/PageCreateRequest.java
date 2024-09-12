package com.sai.bookscribe.messages.page;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageCreateRequest {
    private Integer number;

    private String contexts;

    private Long bookId;
}
