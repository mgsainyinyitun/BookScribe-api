package com.sai.bookscribe.messages.book;

import com.sai.bookscribe.constants.BookTypes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCreateRequest {
    private BookTypes type;
}
