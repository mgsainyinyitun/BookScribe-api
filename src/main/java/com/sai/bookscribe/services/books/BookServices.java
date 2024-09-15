package com.sai.bookscribe.services.books;

import com.sai.bookscribe.constants.BookTypes;
import com.sai.bookscribe.entities.BookEntity;
import com.sai.bookscribe.entities.PageEntity;
import com.sai.bookscribe.entities.UserEntity;
import com.sai.bookscribe.messages.book.*;
import com.sai.bookscribe.messages.page.PageCtxResponse;
import com.sai.bookscribe.repositories.BookRepository;
import org.springframework.stereotype.Service;

import javax.naming.CannotProceedException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
            List<PageCtxResponse> pgCtx = new ArrayList<>();
            for(PageEntity pg:book.getPages()){
                pgCtx.add(new PageCtxResponse(pg.getPageNumber(),pg.getContexts()));
            }
            response.add(new PublicBookResponse(book.getId(),pgCtx,book.getShelf(),book.getNumberOfPage(),book.getBookType(),book.getUser().getUsrName()));
        }
        return response;
    }

    public List<PrivateBookResponse> privateBookRequest(PrivateBookRequest request,UserEntity user) {
        List<BookEntity> books = bookRepository.findByBookTypeAndUser(BookTypes.PRIVATE,user);
        List<PrivateBookResponse> response = new ArrayList<>();

        for(BookEntity book:books){
            List<PageCtxResponse> pgCtx = new ArrayList<>();
            for(PageEntity pg:book.getPages()){
                pgCtx.add(new PageCtxResponse(pg.getPageNumber(),pg.getContexts()));
            }
            response.add(new PrivateBookResponse(book.getId(),pgCtx,book.getShelf(),book.getNumberOfPage(),book.getBookType(),book.getUser().getUsrName()));
        }
        return response;
    }

    public BookDeleteResponse deleteBook(Long id, UserEntity user) throws CannotProceedException {
        BookEntity book = bookRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        if(!book.getUser().getUsername().equals(user.getUsername())){
            throw new CannotProceedException("User not match!");
        }

        bookRepository.delete(book);

        return new BookDeleteResponse(book.getId());
    }
}
