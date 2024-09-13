package com.sai.bookscribe.messages.book;

import com.sai.bookscribe.constants.BookTypes;
import com.sai.bookscribe.constants.ShelfId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCreateRequest {
    private BookTypes type;

    private Integer numberOfPage;

    private ShelfId shelfNumber;
}
