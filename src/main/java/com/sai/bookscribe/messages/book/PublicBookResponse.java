package com.sai.bookscribe.messages.book;

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
}
