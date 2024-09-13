package com.sai.bookscribe.services.books;

import com.sai.bookscribe.constants.BookTypes;
import com.sai.bookscribe.entities.BookEntity;
import com.sai.bookscribe.entities.PageEntity;
import com.sai.bookscribe.entities.UserEntity;
import com.sai.bookscribe.messages.book.BookCreateRequest;
import com.sai.bookscribe.messages.book.BookCreateResponse;
import com.sai.bookscribe.messages.book.PublicBookRequest;
import com.sai.bookscribe.messages.book.PublicBookResponse;
import com.sai.bookscribe.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServices {

    public final BookRepository bookRepository;

    public BookServices(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public BookCreateResponse create(BookCreateRequest request, UserEntity user){
        BookEntity book = new BookEntity();
        book.setBookType(request.getType());
        book.setUser(user);
        book.setNumberOfPage(request.getNumberOfPage());
        book.setShelf(request.getShelfNumber());
        bookRepository.save(book);
        return new BookCreateResponse(book);
    }

    public List<PublicBookResponse> publicBookRequest(){
        List<BookEntity> books = bookRepository.findByBookType(BookTypes.PUBLIC);
        List<PublicBookResponse> response = new ArrayList<>();

        for(BookEntity book:books){
            List<String> pgCtx = new ArrayList<>();
            for(PageEntity pg:book.getPages()){
                pgCtx.add(pg.getContexts());
            }
            response.add(new PublicBookResponse(book.getId(),pgCtx,book.getShelf(),book.getNumberOfPage()));
        }
        return response;
    }

}
