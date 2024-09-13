package com.sai.bookscribe.messages.book;

import com.sai.bookscribe.constants.ShelfId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PublicBookResponse {
    private Long id;

    List<String> text;

    ShelfId shelf;

    Integer numberOfpage;
}
