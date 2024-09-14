package com.sai.bookscribe.services.pages;

import com.sai.bookscribe.entities.BookEntity;
import com.sai.bookscribe.entities.PageEntity;
import com.sai.bookscribe.entities.UserEntity;
import com.sai.bookscribe.messages.page.MultiPageCreateResponse;
import com.sai.bookscribe.messages.page.PageCreateRequest;
import com.sai.bookscribe.messages.page.PageCreateResponse;
import com.sai.bookscribe.repositories.BookRepository;
import com.sai.bookscribe.repositories.PageRepository;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PageServices {
    public  final PageRepository pageRepository;
    public  final BookRepository bookRepository;

    public PageServices(PageRepository pageRepository, BookRepository bookRepository) {
        this.pageRepository = pageRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public PageCreateResponse create(PageCreateRequest request, UserEntity user){
        Optional<BookEntity> book = bookRepository.findById(request.getBookId());
        checkBook(book,user);

        Optional<PageEntity> pg = pageRepository.findByPageNumberAndBookId(request.getNumber(),book.get().getId());
        PageEntity page;

        if(pg.isPresent()){
            // update page and return
            page = pg.get();
            page.setContexts(request.getContexts());
            pageRepository.save(page);
            return new PageCreateResponse(page);
        }

        // create new page & return
        page = createNewPage(book.get(),request);
        pageRepository.save(page);
        return new PageCreateResponse(page);
    }


    @Transactional
    public MultiPageCreateResponse createMulti(List<PageCreateRequest> request, UserEntity user){

        int noOfUpdatePage = 0;

        for(PageCreateRequest req : request){
            Optional<BookEntity> book = bookRepository.findById(req.getBookId());
            checkBook(book,user);

            Optional<PageEntity> pg = pageRepository.findByPageNumberAndBookId(req.getNumber(),book.get().getId());
            PageEntity page;

            if(pg.isPresent()){
                // update page and return
                page = pg.get();
                page.setContexts(req.getContexts());
                pageRepository.save(page);
                noOfUpdatePage++;
            } else {
                // create new book
                pageRepository.save(createNewPage(book.get(),req));
                noOfUpdatePage++;
            }
        }

        return new MultiPageCreateResponse(noOfUpdatePage);
    }

    PageEntity createNewPage(BookEntity book, PageCreateRequest request){
        PageEntity page = new PageEntity();
        page.setBook(book);
        page.setPageNumber(request.getNumber());
        page.setContexts(request.getContexts());
        pageRepository.save(page);
        return page;
    }

    void checkBook( Optional<BookEntity> book,UserEntity user){
        if(book.isEmpty()) {
            throw new ObjectNotFoundException(BookEntity.class,"Book Not Found!");
        }
        if(!book.get().getUser().getUsername().equals(user.getUsername())){
            throw new UsernameNotFoundException("User not match!");
        }
    }

}
