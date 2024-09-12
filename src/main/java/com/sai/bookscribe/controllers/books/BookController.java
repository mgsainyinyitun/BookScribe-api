package com.sai.bookscribe.controllers.books;

import com.sai.bookscribe.constants.AppConstant;
import com.sai.bookscribe.controllers.auth.AuthenticationController;
import com.sai.bookscribe.entities.UserEntity;
import com.sai.bookscribe.messages.book.BookCreateRequest;
import com.sai.bookscribe.messages.book.BookCreateResponse;
import com.sai.bookscribe.services.books.BookServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstant.VERSION+"/books")
public class BookController {

    private  final BookServices bookServices;

    public BookController(BookServices bookServices) {
        this.bookServices = bookServices;
    }

    @GetMapping("public")
    public ResponseEntity<?> getPublicBooks() {

        return  ResponseEntity.ok("public book");
    }

    @GetMapping("private")
    public ResponseEntity<?> getPrivateBooks() {
        UserEntity user = AuthenticationController.getUser();
        System.out.println(user.getEmail());
        return  null;
    }

    @PostMapping("add")
    public ResponseEntity<?> addBook(@RequestBody BookCreateRequest request){
        try{
            BookCreateResponse response;
            response = bookServices.create(request,AuthenticationController.getUser());
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
