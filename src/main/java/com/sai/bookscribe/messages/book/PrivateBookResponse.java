package com.sai.bookscribe.messages.book;

import com.sai.bookscribe.constants.BookTypes;
import com.sai.bookscribe.constants.ShelfId;
import com.sai.bookscribe.messages.page.PageCtxResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class PrivateBookResponse {
    private Long id;

    List<PageCtxResponse> text;

    ShelfId shelf;

    Integer numberOfpage;

    BookTypes bookType;

    String owner;
}
