package com.sai.bookscribe.messages.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageCtxResponse {
    private Integer no;
    private String contexts;
}
