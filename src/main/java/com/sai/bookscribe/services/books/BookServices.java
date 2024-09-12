package com.sai.bookscribe.services.books;


import com.sai.bookscribe.entities.BookEntity;
import com.sai.bookscribe.entities.UserEntity;
import com.sai.bookscribe.messages.book.BookCreateRequest;
import com.sai.bookscribe.messages.book.BookCreateResponse;
import com.sai.bookscribe.repositories.BookRepository;
import org.springframework.stereotype.Service;

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
        bookRepository.save(book);
        return new BookCreateResponse(book);
    }

}
