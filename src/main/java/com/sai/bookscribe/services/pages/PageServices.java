package com.sai.bookscribe.services.pages;


import com.sai.bookscribe.entities.BookEntity;
import com.sai.bookscribe.entities.PageEntity;
import com.sai.bookscribe.entities.UserEntity;
import com.sai.bookscribe.messages.page.PageCreateRequest;
import com.sai.bookscribe.messages.page.PageCreateResponse;
import com.sai.bookscribe.repositories.BookRepository;
import com.sai.bookscribe.repositories.PageRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PageServices {
    public  final PageRepository pageRepository;
    public  final BookRepository bookRepository;

    public PageServices(PageRepository pageRepository, BookRepository bookRepository) {
        this.pageRepository = pageRepository;
        this.bookRepository = bookRepository;
    }

    public PageCreateResponse create(PageCreateRequest request, UserEntity user){
        Optional<BookEntity> book = bookRepository.findById(request.getBookId());
        if(book.isEmpty()) {
            throw new ObjectNotFoundException(BookEntity.class,"Book Not Found!");
        }
        if(!book.get().getUser().getUsername().equals(user.getUsername())){
            throw new UsernameNotFoundException("User not match!");
        }
        PageEntity page = new PageEntity();
        page.setBook(book.get());
        page.setPageNumber(request.getNumber());
        page.setContexts(request.getContexts());
        pageRepository.save(page);
        return new PageCreateResponse(page);
    }
}
